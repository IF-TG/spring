package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.dto.SignUpDto;
import ifTG.travelPlan.dto.user.NicknameDto;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.service.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
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
