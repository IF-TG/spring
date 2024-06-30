package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostLikeService {
    ToggleDto toggleLikePost(Long userId, RequestLikeDto requestLikeDto);

    List<PostWithThumbnailDto> findAllPostLikeWithPostByUser(Long userId, Pageable pageable);
}
