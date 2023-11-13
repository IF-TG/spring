package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestBlockUserDto;
import ifTG.travelPlan.controller.dto.RequestGetAllBlockedUserByUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.user.NicknameAndThumbnail;

import java.util.List;


public interface UserBlockService {

    ToggleDto toggleBlockUser(RequestBlockUserDto dto);

    List<NicknameAndThumbnail> getAllBlockedUserListByUser(Long userId);
}
