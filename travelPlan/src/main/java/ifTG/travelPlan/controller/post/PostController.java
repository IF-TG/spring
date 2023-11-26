package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{userId}")
    public ResponseEntity<Result<List<PostDto>>> getPostByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @PathVariable Long userId){
        return Result.isSuccess(postService.findByUserId(userId, PageRequest.of(page,perPage)));
    }

    @GetMapping("/likedCommented/{userId}")
    public ResponseEntity<Result<List<PostWithThumbnailDto>>> findCommentedOrLikedPostListByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        return Result.isSuccess(postService.findCommentedOrLikedPostListByUserId(userId, PageRequest.of(page, perPage)));
    }

    @PostMapping()
    public ResponseEntity<Result<PostDto>> savePost(@RequestBody PostCreateDto postCreateDto){
        return Result.isSuccess(postService.savePost(postCreateDto));
    }

    @DeleteMapping()
    public ResponseEntity<Result<Boolean>> deletePost(@RequestParam Long postId){
        return Result.isSuccess(postService.deletePost(postId));
    }

    @PutMapping()
    public ResponseEntity<Result<PostDto>> updatePost(@RequestBody PostUpdateDto postUpdateDto){
        return Result.isSuccess(postService.updatePost(postUpdateDto));
    }
}
