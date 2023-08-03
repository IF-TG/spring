package ifTG.travelPlan.service.user;


import ifTG.travelPlan.controller.dto.UserIdDto;

import java.util.List;

public interface UserSearchService {
    List<String> findAllPostSearchByUser(UserIdDto userIdDto);
}
