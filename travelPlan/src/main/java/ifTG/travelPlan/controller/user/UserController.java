package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.service.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@SecurityRequirement(name = "Authorization")
public class UserController {
    private final UserService userService;

    @PatchMapping("/nickname")
    public ResponseEntity<Result<Boolean>> patchUserNickname(
            @RequestParam("nickname") String nickname,
            @AuthenticationUser Long userId
            ){
        return Result.isSuccess(userService.patchNickname(nickname, userId));
    }

    @GetMapping("/nickname/availability")
    public ResponseEntity<Result<Boolean>> isDuplicateNickname(@RequestParam("nickname") String nickname){
        return Result.isSuccess(userService.isDuplicateNickname(nickname));
    }
}
