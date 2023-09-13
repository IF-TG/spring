package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.service.post.PostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postSearch")
public class PostSearchController {
    private final PostSearchService postSearchService;

    @GetMapping
    public Result<List<PostDto>> findAllLikeKeyword(@RequestBody RequestSearchPostDto requestSearchPostDto){
        return new Result<>(postSearchService.findAllLikeKeyword(requestSearchPostDto));
    }

}
