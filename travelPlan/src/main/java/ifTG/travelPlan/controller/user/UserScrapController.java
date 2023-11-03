package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestRenameScrapFolder;
import ifTG.travelPlan.controller.dto.RequestScrapFolderDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.ScrapPostAndDestination;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;
import ifTG.travelPlan.service.user.UserScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public Result<List<UserScrapFolderDto>> renameScrapFolder(@RequestBody RequestRenameScrapFolder dto){
        return new Result<>(userScrapService.renameScrapFolder(dto));
    }
}
