package ifTG.travelPlan.service.user;


import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.domain.user.User;

import java.util.List;

public interface UserSearchService {
    List<String> findAllSearchHistoryByUser(RequestSearchHistoryPageDto requestSearchHistoryPageDto);

    void saveKeywordHistory(User user, String keyword);
}
