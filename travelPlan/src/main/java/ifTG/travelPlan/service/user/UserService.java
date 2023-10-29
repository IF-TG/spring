package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;

public interface UserService {
    UserInfoDto saveUser(UserCreateDto userCreateDto);
}
