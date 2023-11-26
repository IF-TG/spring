package ifTG.travelPlan.controller.post;


import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.post.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/postLike")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;
    @PostMapping
    public ResponseEntity<Result<ToggleDto>> toggleLikePost(@RequestBody RequestLikeDto requestLikeDto){
        return Result.isSuccess(postLikeService.toggleLikePost(requestLikeDto));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<Result<List<PostWithThumbnailDto>>> findAllPostLikeWithPostByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @PathVariable Long userId){
        return Result.isSuccess(postLikeService.findAllPostLikeWithPostByUser(userId, PageRequest.of(page, perPage)));
    }
}
