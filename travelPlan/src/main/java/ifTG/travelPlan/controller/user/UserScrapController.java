package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.RequestScrapFolderDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.ScrapPostAndDestination;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;
import ifTG.travelPlan.service.user.UserScrapService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userScrap")
@Slf4j
@RequiredArgsConstructor
public class UserScrapController {
    private final UserScrapService userScrapService;

    @GetMapping
    public Result<List<UserScrapFolderDto>> findAllScrapByUser(@RequestBody RequestScrapFolderDto dto){
        return new Result<>(userScrapService.findAllScrapFolderByUser(dto));
    }

    @GetMapping("/detail")
    public Result<List<ScrapPostAndDestination>> findAllScrapsByScrapFolderAndUserId(@RequestBody RequestScrapDetail dto){
        return new Result<>(userScrapService.findAllScrapsByScrapFolderAndUserId(dto));
    }
}
