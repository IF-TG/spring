package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.SearchHistoryDto;
import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.repository.springdata.user.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserSearchServiceImpl implements UserSearchService{
    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public List<SearchHistoryDto> findAllSearchHistoryByUser(Long userId, Pageable pageable) {
        return searchHistoryRepository.findAllByUserId(userId, pageable).stream()
                                      .map(sh-> new SearchHistoryDto(sh.getHistory(), sh.getSearch_time())).toList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveKeywordHistory(User user, String keyword) {
        log.info("user save KeywordHistory = {}, {}", user.getId(), keyword);
        searchHistoryRepository.save(new SearchHistory(user, keyword));
    }
}
