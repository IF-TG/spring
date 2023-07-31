package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.annotation.ToggleLike;
import ifTG.travelPlan.controller.post.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;

public interface NestedCommentLikeService {
    @ToggleLike
    LikeDto toggleLikeNestedComment(RequestLikeDto requestLikeDto);
}
