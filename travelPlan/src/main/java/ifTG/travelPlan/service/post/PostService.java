package ifTG.travelPlan.service.post;

import ifTG.travelPlan.dto.post.PostCreateDto;
import ifTG.travelPlan.dto.post.PostDeleteDto;
import ifTG.travelPlan.dto.post.PostRequestDto;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.dto.post.PostUpdateDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPostWithPostRequestDto(PostRequestDto postRequestDto);
    PostDto savePost(PostCreateDto postCreateDto);
    Boolean deletePost(PostDeleteDto postDeleteDto);
    PostDto updatePost(PostUpdateDto postUpdateDto);
}
