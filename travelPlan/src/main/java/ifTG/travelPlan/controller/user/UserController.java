package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private UserService userService;

    @PostMapping
    public UserInfoDto saveUser(UserCreateDto userCreateDto){
        return userService.saveUser(userCreateDto);
    }



}
