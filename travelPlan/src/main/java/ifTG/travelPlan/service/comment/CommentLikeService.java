package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.ToggleDto;

public interface CommentLikeService {

    ToggleDto toggleLikeComment(RequestLikeDto requestLikeDto);


}
