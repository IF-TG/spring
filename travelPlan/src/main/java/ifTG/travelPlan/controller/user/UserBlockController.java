package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.RequestBlockUserDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.user.NicknameAndThumbnail;
import ifTG.travelPlan.service.user.UserBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blockUser")
@RequiredArgsConstructor
public class UserBlockController {
    private final UserBlockService userBlockService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> blockedUser(@AuthenticationUser Long userId, @RequestBody RequestBlockUserDto dto){
        return Result.isSuccess(userBlockService.toggleBlockUser(userId, dto));
    }

    @GetMapping("/list")
    public ResponseEntity<Result<List<NicknameAndThumbnail>>> getBlockedUserListByUser(@AuthenticationUser Long userId){
        return Result.isSuccess(userBlockService.getAllBlockedUserListByUser(userId));
    }
}
