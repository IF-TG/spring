package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import ifTG.travelPlan.dto.post.enums.OrderMethod;
import ifTG.travelPlan.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public Result<List<PostWithThumbnailDto>> getPostListWithCategory(
            @RequestParam int page,
            @RequestParam int perPage,
            @RequestParam OrderMethod orderMethod,
            @RequestParam MainCategory mainCategory,
            @RequestParam String subCategory,
            @RequestParam Long userId) throws IllegalAccessException {
        return new Result<>(postService.findAllPostWithPostRequestDto(new RequestPostListDto(page, perPage, orderMethod, mainCategory, subCategory, userId)));
    }

    @GetMapping("/{id}")
    public Result<List<PostDto>> getPostByUserId(
            @PathVariable("id") Long userId,
            @RequestParam int page,
            @RequestParam int perPage){
        return new Result<>(postService.findByUserId(new RequestPostListByUserIdDto(page, perPage, userId)));
    }

    @GetMapping("/liked-commented/{id}")
    public Result<List<PostWithThumbnailDto>> findCommentedOrLikedPostListByUserId(@RequestBody RequestAllUserLikeOrCommentPostDto dto){
        return new Result<>(postService.findCommentedOrLikedPostListByUserId(dto));
    }

    @PostMapping()
    public PostDto savePost(@RequestBody PostCreateDto postCreateDto){
        return postService.savePost(postCreateDto);
    }

    @DeleteMapping()
    public Boolean deletePost(@RequestBody PostIdDto postIdDto){
        return postService.deletePost(postIdDto);
    }

    @PutMapping()
    public PostDto updatePost(@RequestBody PostUpdateDto postUpdateDto){
        return postService.updatePost(postUpdateDto);
    }
}
