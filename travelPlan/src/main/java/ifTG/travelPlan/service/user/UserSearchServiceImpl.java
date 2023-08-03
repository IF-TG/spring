package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.UserIdDto;
import ifTG.travelPlan.domain.user.SearchHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService{
    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public List<String> findAllPostSearchByUser(UserIdDto userIdDto) {
        return null;
    }
}
