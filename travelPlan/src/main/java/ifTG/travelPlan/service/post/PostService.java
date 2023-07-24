package ifTG.travelPlan.service.post;

import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.controller.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPostWithPostRequestDto(PostRequestDto postRequestDto);
    PostDto savePost(PostCreateDto postCreateDto);
    Boolean deletePost(PostIdDto postIdDto);
    PostDto updatePost(PostUpdateDto postUpdateDto);

}
