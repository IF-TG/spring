package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.dto.user.NicknameDto;

public interface UserService {
    UserInfoDto saveUser(UserCreateDto userCreateDto);

    Boolean patchNickname(String nickname, Long userId);

    Boolean isDuplicateNickname(String nickname);
}
