package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.controller.dto.RequestPostListByUserIdDto;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.ToggleDto;

import java.util.List;

public interface PostLikeService {
    ToggleDto toggleLikePost(RequestLikeDto requestLikeDto);

    List<PostWithThumbnailDto> findAllPostLikeWithPostByUser(RequestPostListByUserIdDto dto);
}
