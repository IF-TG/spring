package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestPostListByUserIdDto;
import ifTG.travelPlan.dto.post.*;

import java.util.List;

public interface PostService {

    List<PostWithThumbnailDto> findAllPostWithPostRequestDto(RequestPostListDto requestPostListDto);
    PostDto savePost(PostCreateDto postCreateDto);
    Boolean deletePost(PostIdDto postIdDto);
    PostDto updatePost(PostUpdateDto postUpdateDto);
    List<PostDto> findByUserId(RequestPostListByUserIdDto userIdDto);
}
