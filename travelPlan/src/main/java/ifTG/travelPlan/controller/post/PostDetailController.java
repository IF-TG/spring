package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;
import ifTG.travelPlan.service.post.PostDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/postDetail")
@RequiredArgsConstructor
public class PostDetailController {
    private final PostDetailService postDetailService;
    @GetMapping
    public ResponseEntity<Result<PostDetailsWithIsScraped>> getPostDetail(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam Long postId,
            @RequestParam Long userId){
        return Result.isSuccess(postDetailService.getPostDetail(postId, userId, PageRequest.of(page,perPage)));
    }
}
