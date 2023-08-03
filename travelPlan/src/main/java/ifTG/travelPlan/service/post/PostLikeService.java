package ifTG.travelPlan.service.post;

import ifTG.travelPlan.annotation.ToggleLike;
import ifTG.travelPlan.controller.post.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;

public interface PostLikeService {
    @ToggleLike
    LikeDto toggleLikePost(RequestLikeDto requestLikeDto);
}
