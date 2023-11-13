package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.SearchHistoryRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DestinationSearchServiceImpl implements DestinationSearchService{
    private final ChatGPT chatGPT;
    private final EDestinationCustomRepository eDestinationCustomRepository;
    private final Map<String, RelatedKeyword> chatGptRelatedKeywordListMap = new HashMap<>();
    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;
    private final DestinationScrapRepository destinationScrapRepository;
    private final int fixedRate = 600000;
    private final int resizingSize = 1000;
    @Getter
    @AllArgsConstructor
    private static class RelatedKeyword implements Comparable<RelatedKeyword> {
        private List<String> keywordList;
        private LocalTime createdAt;

        @Override
        public int compareTo(RelatedKeyword o) {
            return this.createdAt.compareTo(o.getCreatedAt());
        }
    }

    @Getter
    @AllArgsConstructor
    private static class RelatedKeywordWithString{
        private String keyword;
        private RelatedKeyword relatedKeyword;
    }
    @Override
    public List<ResponseEDestinationDto> findAllByKeyword(Long userId, String keyword, Pageable pageable){
        ResponseFindEDestinationList response = findEDestinationList(keyword, pageable);
        List<EDestination> eDestinationList = response.getEDestinationList();
        List<Long> destinationScrapIdList = destinationScrapRepository.findAllWithDestinationByUserId(userId)
                                                                      .stream().map(ds->ds.getDestination().getId()).toList();
        new Thread(()->saveSearchHistory(keyword, userId)).start();
        return eDestinationList.stream().map(ed->ResponseEDestinationDto.builder()
                                                                        .id(ed.getId())
                                                                        .title(ed.getTitle())
                                                                        .thumbnailUrl(ed.getThumbnailUrl())
                                                                        .address(ed.getAddress())
                                                                        .largeCategory(ed.getCategory().getLargeCategory().getValue())
                                                                        .middleCategory(ed.getCategory().getMiddleCategory().getValue())
                                                                        .smallCategory(ed.getCategory().getSmallCategory().getValue())
                                                                        .contentType(ed.getContentType())
                                                                        .isScraped(destinationScrapIdList.contains(ed.getId()))
                                                                        .isGptRelated(response.isGptRelated)
                                                                        .build()).toList();
    }
    private void saveSearchHistory(String keyword, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("not found user"));
        searchHistoryRepository.save(new SearchHistory(user, keyword));
    }

    @Getter
    @AllArgsConstructor
    private static class ResponseFindEDestinationList{
        private List<EDestination> eDestinationList;
        private boolean isGptRelated;
    }
    private ResponseFindEDestinationList findEDestinationList(String keyword, Pageable pageable) {
        List<String> relatedKeywordList;
        boolean isGptRelated = false;
        if (chatGptRelatedKeywordListMap.containsKey(keyword)){
            relatedKeywordList = chatGptRelatedKeywordListMap.get(keyword).getKeywordList();
            isGptRelated = true;
        }else{
            new Thread(()->chatGptRelatedKeywordListMap.put(keyword, new RelatedKeyword(chatGPT.findRelatedKeywords(keyword), LocalTime.now()))).start();
            relatedKeywordList = new ArrayList<>();
        }
        List<EDestination> eDestinationList = eDestinationCustomRepository.findAllByUserKeywordAndGPTKeywordList(keyword, relatedKeywordList , pageable);
        return new ResponseFindEDestinationList(eDestinationList, isGptRelated);
    }

    @Scheduled(fixedRate = fixedRate)
    public void managementMap(){
        log.info("Scheduled start > resizing the chatGptRelatedKeywordListMap");
        PriorityQueue<RelatedKeywordWithString> q = new PriorityQueue<>(Comparator.comparing(RelatedKeywordWithString::getRelatedKeyword));
        chatGptRelatedKeywordListMap.keySet().forEach(c->q.add(new RelatedKeywordWithString(c, chatGptRelatedKeywordListMap.get(c))));
        while(resizingSize<chatGptRelatedKeywordListMap.size()){
            chatGptRelatedKeywordListMap.remove(q.poll().getKeyword());
        }
    }
}
