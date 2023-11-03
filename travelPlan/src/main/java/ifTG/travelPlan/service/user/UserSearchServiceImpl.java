package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.SearchHistoryDto;
import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.repository.springdata.user.SearchHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserSearchServiceImpl implements UserSearchService{
    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public List<SearchHistoryDto> findAllSearchHistoryByUser(RequestSearchHistoryPageDto dto) {
        return searchHistoryRepository.findAllByUserId(dto.getUserId(), dto.getPageable()).stream()
                                      .map(sh-> new SearchHistoryDto(sh.getHistory(), sh.getSearch_time())).toList();
    }

    @Override
    public void saveKeywordHistory(User user, String keyword) {
        log.info("{}, {}", user.getId(), keyword);
        Optional<SearchHistory> searchHistory = searchHistoryRepository.findByUserIdAndHistory(user.getId(), keyword);
        if(searchHistory.isEmpty()){
            searchHistoryRepository.save(new SearchHistory(user, keyword));
        }else{
            searchHistory.get().updateTimeSearchHistory();
        }
    }
}
