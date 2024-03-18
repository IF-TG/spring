package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import ifTG.travelPlan.service.user.UserSearchService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DestinationSearchServiceImpl implements DestinationSearchService{
    private final ChatGPT chatGPT;
    private final EDestinationCustomRepository eDestinationCustomRepository;
    private final UserSearchService userSearchService;
    private final UserRepository userRepository;
    private final DestinationScrapRepository destinationScrapRepository;
    private final EDestinationConvertDtoService EDestinationConvertDtoService;
    private final UserKeywordRedisCache userKeywordRedisCache;

    @Override
    public List<ResponseEDestinationDto> findAllByKeyword(Long userId, String keyword, Pageable pageable){
        saveSearchHistory(userId, keyword);
        ResponseFindEDestinationList response = findEDestinationList(keyword, pageable);
        List<EDestination> eDestinationList = response.getEDestinationList();
        List<Long> destinationScrapIdList = destinationScrapRepository.findAllDestinationIdByUserId(userId);
        return EDestinationConvertDtoService.getResponseEDestinationDtoList(response.isGptRelated, eDestinationList, destinationScrapIdList);
    }

    private void saveSearchHistory(Long userId, String keyword) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        userSearchService.saveKeywordHistory(user, keyword);
    }


    @Getter
    @AllArgsConstructor
    private static class ResponseFindEDestinationList{
        private List<EDestination> eDestinationList;
        private boolean isGptRelated;
    }
    private ResponseFindEDestinationList findEDestinationList(String keyword, Pageable pageable) {
        boolean isGptRelated = false;
        List<String> relatedKeyword = userKeywordRedisCache.getWordList(keyword);
        if (relatedKeyword != null){
            isGptRelated = true;
        }else{
            chatGPT.findRelatedKeywords(keyword).thenAccept(result->userKeywordRedisCache.addWordList(keyword, result));
            relatedKeyword = new ArrayList<>();
        }
        List<EDestination> eDestinationList = eDestinationCustomRepository.findAllByUserKeywordAndGPTKeywordList(keyword, relatedKeyword , pageable);
        return new ResponseFindEDestinationList(eDestinationList, isGptRelated);
    }

}
