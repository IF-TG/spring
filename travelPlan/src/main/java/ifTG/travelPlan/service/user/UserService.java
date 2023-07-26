package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.user.UserCreateDto;
import ifTG.travelPlan.controller.user.UserInfoDto;

public interface UserService {
    UserInfoDto saveUser(UserCreateDto userCreateDto);
}
