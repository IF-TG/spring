package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.dto.user.NicknameDto;
import ifTG.travelPlan.service.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping
    @Hidden
    public UserInfoDto saveUser(UserCreateDto userCreateDto){
        return userService.saveUser(userCreateDto);
    }

    @PatchMapping("/nickname")
    public Boolean patchUserNickname(
            @RequestParam("nickname") String nickname,
            @RequestParam("userId") Long userId
            ){
        return userService.patchNickname(nickname, userId);
    }

    @GetMapping("/nickname")
    public Boolean isDuplicateNickname(@RequestParam("nickname") String nickname){
        return userService.isDuplicateNickname(nickname);
    }
}
