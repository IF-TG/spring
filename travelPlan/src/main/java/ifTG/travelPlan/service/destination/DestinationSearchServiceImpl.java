package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import ifTG.travelPlan.service.user.UserSearchService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DestinationSearchServiceImpl implements DestinationSearchService{
    private final ChatGPT chatGPT;
    private final EDestinationCustomRepository eDestinationCustomRepository;
    private final Map<String, RelatedKeyword> chatGptRelatedKeywordListMap = new HashMap<>();
    private final UserSearchService userSearchService;
    private final UserRepository userRepository;
    private final DestinationScrapRepository destinationScrapRepository;
    private final EDestinationConvertDtoService EDestinationConvertDtoService;
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

        public void updateTime(LocalTime now) {
            createdAt = now;
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
        saveSearchHistory(userId, keyword);
        ResponseFindEDestinationList response = findEDestinationList(keyword, pageable);
        List<EDestination> eDestinationList = response.getEDestinationList();
        List<Long> destinationScrapIdList = destinationScrapRepository.findAllDestinationIdByUserId(userId);
        return EDestinationConvertDtoService.getResponseEDestinationDtoList(response.isGptRelated, eDestinationList, destinationScrapIdList);
    }

    private void saveSearchHistory(Long userId, String keyword) {
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("not found user"));
        userSearchService.saveKeywordHistory(user, keyword);
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
            RelatedKeyword relatedKeyword = chatGptRelatedKeywordListMap.get(keyword);
            relatedKeyword.updateTime(LocalTime.now());
            relatedKeywordList = relatedKeyword.getKeywordList();
            isGptRelated = true;
        }else{
            chatGPT.findRelatedKeywords(keyword).thenApply(result->chatGptRelatedKeywordListMap.put(keyword, new RelatedKeyword(result, LocalTime.now())));
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
