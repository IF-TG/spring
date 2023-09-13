package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PostConvertDto {
    List<PostDto> getPostDtoList(Page<Post> postList, List<Long> likedPostIdList);
    List<PostDto> getPostDtoList(List<Post> postList, List<Long> likedPostIdList);
    PostDto getPostDto(Post post, boolean isLiked);
    List<PostWithThumbnailDto> getPostWithThumbnailDtoList(Page<Post> postList, Map<Long, List<ImageToString>> thumbnailMap, List<Long> likedPostListByUser);
}
