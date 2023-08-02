package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.domain.post.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostConvertDto {
    List<PostDto> getPostDtoList(Page<Post> postList, List<Long> likedPostIdList);

    List<PostDto> getPostDtoList(List<Post> postList, List<Long> likedPostIdList);

    PostDto getPostDto(Post post, boolean isLiked);
}
