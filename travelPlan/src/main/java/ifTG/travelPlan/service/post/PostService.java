package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestAllUserLikeOrCommentPostDto;
import ifTG.travelPlan.controller.dto.RequestPostListByUserIdDto;
import ifTG.travelPlan.dto.post.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<PostWithThumbnailDto> findAllPostWithPostRequestDto(RequestPostListDto requestPostListDto);
    PostDto savePost(PostCreateDto postCreateDto);
    Boolean deletePost(Long postId);
    PostDto updatePost(PostUpdateDto postUpdateDto);
    List<PostDto> findByUserId(Long userId, Pageable pageable);

    List<PostWithThumbnailDto> findCommentedOrLikedPostListByUserId(Long userId, Pageable pageable);
}
