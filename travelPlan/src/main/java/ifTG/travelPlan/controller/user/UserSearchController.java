package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.UserIdDto;
import ifTG.travelPlan.service.user.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searchHistory")
public class UserSearchController {
    private final UserSearchService userSearchService;
    @GetMapping
    public Result<List<String>> findAllPostSearchByUser(UserIdDto userIdDto){
        return new Result<>(userSearchService.findAllPostSearchByUser(userIdDto));
    }
}
