package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.dto.SignUpDto;
import ifTG.travelPlan.dto.user.NicknameDto;

public interface UserService {

    Boolean patchNickname(String nickname, Long userId);

    Boolean isDuplicateNickname(String nickname);
}
