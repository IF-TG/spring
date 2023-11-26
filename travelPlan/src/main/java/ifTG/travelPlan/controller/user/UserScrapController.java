package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.RequestRenameScrapFolder;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;
import ifTG.travelPlan.service.user.UserScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userScrap")
@Slf4j
@RequiredArgsConstructor
public class UserScrapController {
    private final UserScrapService userScrapService;

    @GetMapping
    public ResponseEntity<Result<List<UserScrapFolderDto>>> findAllScrapByUser(@RequestParam Long userId){
        return Result.isSuccess(userScrapService.findAllScrapFolderByUser(userId));
    }

    @PutMapping
    public ResponseEntity<Result<List<UserScrapFolderDto>>> renameScrapFolder(@RequestBody RequestRenameScrapFolder dto){
        return Result.isSuccess(userScrapService.renameScrapFolder(dto));
    }
}
