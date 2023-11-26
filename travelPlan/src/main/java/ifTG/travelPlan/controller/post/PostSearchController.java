package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.service.post.PostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postSearch")
public class PostSearchController {
    private final PostSearchService postSearchService;

    @GetMapping
    public ResponseEntity<Result<List<PostDto>>> findAllLikeKeyword(
            @RequestParam String keyword,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "true") boolean isTitle,
            @RequestParam(defaultValue = "true") boolean isContent,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        return Result.isSuccess(postSearchService.findAllLikeKeyword(new RequestSearchPostDto(keyword, userId, isTitle, isContent, page, perPage)));
    }

}
