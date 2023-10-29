package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestPostListByUserIdDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.*;
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
    public Result<List<PostWithThumbnailDto>> getPostListWithCategory(@RequestBody RequestPostListDto requestPostListDto){
        return new Result<>(postService.findAllPostWithPostRequestDto(requestPostListDto));
    }

    @GetMapping("/byUser")
    public Result<List<PostDto>> getPostByUserId(@RequestBody RequestPostListByUserIdDto dto ){
        return new Result<>(postService.findByUserId(dto));
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
