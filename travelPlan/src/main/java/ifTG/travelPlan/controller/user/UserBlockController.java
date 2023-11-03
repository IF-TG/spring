package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.RequestBlockUserDto;
import ifTG.travelPlan.controller.dto.RequestGetAllBlockedUserByUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.user.NicknameAndThumbnail;
import ifTG.travelPlan.service.user.UserBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blockUser")
@RequiredArgsConstructor
public class UserBlockController {
    private final UserBlockService userBlockService;

    @PostMapping
    public ToggleDto blockedUser(@RequestBody RequestBlockUserDto dto){
        return userBlockService.toggleBlockUser(dto);
    }

    @GetMapping("/list")
    public Result<List<NicknameAndThumbnail>> getBlockedUserListByUser(@RequestBody RequestGetAllBlockedUserByUser dto){
        return new Result<>(userBlockService.getAllBlockedUserListByUser(dto));
    }
}
