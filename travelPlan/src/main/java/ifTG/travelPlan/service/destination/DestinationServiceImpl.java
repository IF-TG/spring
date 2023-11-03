package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
<<<<<<< HEAD
import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.SearchHistoryRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
=======
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de

@Service
@RequiredArgsConstructor
@Slf4j
public class DestinationServiceImpl implements DestinationService{
    private final ChatGPT chatGPT;
    private final EDestinationCustomRepository eDestinationCustomRepository;
<<<<<<< HEAD
    private final Map<String, RelatedKeyword> chatGptRelatedKeywordListMap = new HashMap<>();
    private final DestinationScrapRepository destinationScrapRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;
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
    public List<ResponseEDestinationDto> findAllByKeyword(RequestSearchDestinationDto dto){
        ResponseFindEDestinationList response = findEDestinationList(dto);
        List<EDestination> eDestinationList = response.getEDestinationList();
        List<Long> destinationScrapIdList = destinationScrapRepository.findAllWithDestinationByUserId(dto.getUserId())
                .stream().map(ds->ds.getDestination().getId()).toList();
        new Thread(()->saveSearchHistory(dto.getKeyword(), dto.getUserId())).start();
        return eDestinationList.stream().map(ed->ResponseEDestinationDto.builder()
                .id(ed.getId())
                .title(ed.getTitle())
                .thumbnailUrl(ed.getThumbnailUrl())
                .address(ed.getAddress())
                .LargeCategory(ed.getCategory().getLargeCategory().getValue())
                .MiddleCategory(ed.getCategory().getMiddleCategory().getValue())
                .SmallCategory(ed.getCategory().getSmallCategory().getValue())
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
    private ResponseFindEDestinationList findEDestinationList(RequestSearchDestinationDto dto) {
        List<String> relatedKeywordList;
        boolean isGptRelated = false;
        if (chatGptRelatedKeywordListMap.containsKey(dto.getKeyword())){
            relatedKeywordList = chatGptRelatedKeywordListMap.get(dto.getKeyword()).getKeywordList();
            isGptRelated = true;
        }else{
            new Thread(()->chatGptRelatedKeywordListMap.put(dto.getKeyword(), new RelatedKeyword(chatGPT.findRelatedKeywords(dto.getKeyword()), LocalTime.now()))).start();
            relatedKeywordList = new ArrayList<>();
        }
        List<EDestination> eDestinationList = eDestinationCustomRepository.findAllByUserKeywordAndGPTKeywordList(dto.getKeyword(), relatedKeywordList , dto.getPageable());
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
=======
    @Override
    public List<EDestination> findAllByKeyword(RequestSearchDestinationDto dto){
        List<String> relatedKeywordList  = chatGPT.findRelatedKeywords(dto.getKeyword());
        log.info("{}",relatedKeywordList.toString());
        return eDestinationCustomRepository.findAllByUserKeywordAndGPTKeywordList(dto.getKeyword(), relatedKeywordList, dto.getPageable());
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
    }
}
