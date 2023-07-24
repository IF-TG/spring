package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class postController {
    private final PostService postService;

    @GetMapping("/list")
    public Result<List<PostDto>> getPostListWithCategory(@RequestBody PostRequestDto postRequestDto){
        return new Result<>(postService.findAllPostWithPostRequestDto(postRequestDto));
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
    public PostDto deletePost(@RequestBody PostUpdateDto postUpdateDto){
        return postService.updatePost(postUpdateDto);
    }


}
