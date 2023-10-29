package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.ToggleDto;

public interface PostLikeService {
    ToggleDto toggleLikePost(RequestLikeDto requestLikeDto);
}
