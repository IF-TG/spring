package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.RequestScrapDetail;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.post.PostScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/postScrap")
@RequiredArgsConstructor
public class PostScrapController {
    private final PostScrapService postScrapService;

    @PostMapping
    public ToggleDto togglePostScrap(@RequestBody RequestScrapDto dto){
        return postScrapService.togglePostScrap(dto);
    }

    @PutMapping
    public ScrapDto updateFolderName(@RequestBody RequestScrapDto dto) {
        return postScrapService.updateFolderName(dto);
    }
    @GetMapping("/detail")
    public Result<List<PostDto>> findAllPostScrapsByScrapFolderAndUserId(@RequestBody RequestScrapDetail dto){
        return new Result<>(postScrapService.findAllPostScrapsByScrapFolderAndUserId(dto));
    }
}
