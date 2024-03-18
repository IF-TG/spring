package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;
import ifTG.travelPlan.service.post.PostDetailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/post/detail")
@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
public class PostDetailController {
    private final PostDetailService postDetailService;
    @GetMapping
    public ResponseEntity<Result<PostDetailsWithIsScraped>> getPostDetail(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam Long postId,
            @AuthenticationUser Optional<Long> userId){
        return userId.map(aLong -> Result.isSuccess(postDetailService.getPostDetail(postId, aLong, PageRequest.of(page, perPage)))).orElseGet(() -> Result.isSuccess(postDetailService.getPostDetail(postId, PageRequest.of(page, perPage))));

    }
}
