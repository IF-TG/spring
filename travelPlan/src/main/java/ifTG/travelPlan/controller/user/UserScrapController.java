package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.RequestCreateScrapFolderDto;

import ifTG.travelPlan.controller.dto.RequestGetAllScrapByUserDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.user.ScrapFolderDto;
import ifTG.travelPlan.dto.user.ScrapTitleDto;
import ifTG.travelPlan.service.user.UserScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class UserScrapController {
    private final UserScrapService userScrapService;
    @PostMapping
    public ScrapTitleDto createScrapFolder(@RequestBody RequestCreateScrapFolderDto requestCreateScrapFolderDto){
        return userScrapService.createScrapFolder(requestCreateScrapFolderDto);
    }

    @GetMapping
    public Result<List<ScrapFolderDto>> findAllScrapByUser(@RequestBody RequestGetAllScrapByUserDto dto){
        return new Result<>(userScrapService.findAllScrapByUser(dto));
    }
    /*
    @PostMapping
    public Boolean insertPostScrap(@RequestBody)*/
}
