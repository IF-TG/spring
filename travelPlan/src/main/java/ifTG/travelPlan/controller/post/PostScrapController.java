package ifTG.travelPlan.controller.post;

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
@RequestMapping("/postScrap")
@RequiredArgsConstructor
public class PostScrapController {
    private final PostScrapService postScrapService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> togglePostScrap(@RequestBody RequestScrapDto dto){
        return Result.isSuccess(postScrapService.togglePostScrap(dto));
    }

    @PutMapping
    public ResponseEntity<Result<List<ScrapDto>>> updateFolderName(@RequestBody RequestUpdatePostScrapDto dto) {
        return Result.isSuccess(postScrapService.updateFolderName(dto));
    }
    @GetMapping("/detail")
    public ResponseEntity<Result<List<PostDto>>> findAllPostScrapsByScrapFolderAndUserId(
            @RequestParam String folderName,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        return Result.isSuccess(postScrapService.findAllPostScrapsByScrapFolderAndUserId(folderName, userId, PageRequest.of(page,perPage)));
    }
}
