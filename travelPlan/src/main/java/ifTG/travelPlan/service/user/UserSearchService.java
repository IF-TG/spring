package ifTG.travelPlan.service.user;


import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.SearchHistoryDto;
import ifTG.travelPlan.domain.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserSearchService {
    List<SearchHistoryDto> findAllSearchHistoryByUser(Long userId, Pageable pageable);

    void saveKeywordHistory(User user, String keyword);
}
