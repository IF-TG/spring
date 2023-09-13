package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;

public interface CommentLikeService {

    LikeDto toggleLikeComment(RequestLikeDto requestLikeDto);


}
