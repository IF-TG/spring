package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;

public interface PostLikeService {
    LikeDto toggleLikePost(RequestLikeDto requestLikeDto);
}
