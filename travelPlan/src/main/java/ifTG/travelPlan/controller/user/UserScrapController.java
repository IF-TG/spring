package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.RequestRenameScrapFolder;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;
import ifTG.travelPlan.service.scrap.ScrapDeleteService;
import ifTG.travelPlan.service.user.UserScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scrap")
@Slf4j
@RequiredArgsConstructor
public class UserScrapController {
    private final UserScrapService userScrapService;
    private final ScrapDeleteService scrapDeleteService;
    @DeleteMapping()
    public ResponseEntity<Result<Boolean>> deleteAllByFolderName(@AuthenticationUser Long userId, @RequestParam String folderName){
        return Result.isSuccess(scrapDeleteService.deleteAllByFolderName(userId, folderName));
    }
    @GetMapping
    public ResponseEntity<Result<List<UserScrapFolderDto>>> findAllScrapByUser(@AuthenticationUser Long userId){
        return Result.isSuccess(userScrapService.findAllScrapFolderByUser(userId));
    }

    @PutMapping
    public ResponseEntity<Result<List<UserScrapFolderDto>>> renameScrapFolder(@AuthenticationUser Long userId, @RequestBody RequestRenameScrapFolder dto){
        return Result.isSuccess(userScrapService.renameScrapFolder(userId, dto));
    }
}
