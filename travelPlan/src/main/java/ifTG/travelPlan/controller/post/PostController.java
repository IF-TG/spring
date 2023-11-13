package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import ifTG.travelPlan.dto.post.enums.OrderMethod;
import ifTG.travelPlan.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{userId}")
    public Result<List<PostDto>> getPostByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @PathVariable Long userId){
        return new Result<>(postService.findByUserId(userId, PageRequest.of(page,perPage)));
    }

    @GetMapping("/likedCommented/{userId}")
    public Result<List<PostWithThumbnailDto>> findCommentedOrLikedPostListByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        return new Result<>(postService.findCommentedOrLikedPostListByUserId(userId, PageRequest.of(page, perPage)));
    }

    @PostMapping()
    public PostDto savePost(@RequestBody PostCreateDto postCreateDto){
        return postService.savePost(postCreateDto);
    }

    @DeleteMapping()
    public Boolean deletePost(@RequestParam Long postId){
        return postService.deletePost(postId);
    }

    @PutMapping()
    public PostDto updatePost(@RequestBody PostUpdateDto postUpdateDto){
        return postService.updatePost(postUpdateDto);
    }
}
