package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.post.PostScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post/scrap")
@RequiredArgsConstructor
public class PostScrapController {
    private final PostScrapService postScrapService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> togglePostScrap(@AuthenticationUser Long userId, @RequestBody RequestScrapDto dto){
        return Result.isSuccess(postScrapService.togglePostScrap(userId, dto));
    }

    @PutMapping
    public ResponseEntity<Result<List<ScrapDto>>> updateFolderName(@AuthenticationUser Long userId, @RequestBody RequestUpdatePostScrapDto dto) {
        return Result.isSuccess(postScrapService.updateFolderName(userId, dto));
    }
    @GetMapping("/detail")
    public ResponseEntity<Result<List<PostDto>>> findAllPostScrapsByScrapFolderAndUserId(
            @RequestParam String folderName,
            @AuthenticationUser Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        return Result.isSuccess(postScrapService.findAllPostScrapsByScrapFolderAndUserId(folderName, userId, PageRequest.of(page,perPage)));
    }
}
