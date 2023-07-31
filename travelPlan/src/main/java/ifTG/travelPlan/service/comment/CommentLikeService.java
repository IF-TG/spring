package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.annotation.ToggleLike;
import ifTG.travelPlan.controller.post.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;

public interface CommentLikeService {

    @ToggleLike
    LikeDto toggleLikeComment(RequestLikeDto requestLikeDto);


}
