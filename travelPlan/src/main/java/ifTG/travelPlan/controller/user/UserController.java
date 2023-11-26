package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.service.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping
    @Hidden
    public ResponseEntity<Result<UserInfoDto>> saveUser(UserCreateDto userCreateDto){
        return Result.isSuccess(userService.saveUser(userCreateDto));
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Result<Boolean>> patchUserNickname(
            @RequestParam("nickname") String nickname,
            @RequestParam("userId") Long userId
            ){
        return Result.isSuccess(userService.patchNickname(nickname, userId));
    }

    @GetMapping("/nickname")
    public ResponseEntity<Result<Boolean>> isDuplicateNickname(@RequestParam("nickname") String nickname){
        return Result.isSuccess(userService.isDuplicateNickname(nickname));
    }
}
