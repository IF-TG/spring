package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.aop.ToggleLike;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NestedCommentLikeServiceImpl implements NestedCommentLikeService{
    @Override
    @ToggleLike
    public ToggleDto toggleLikeNestedComment(RequestLikeDto requestLikeDto) {
        return null;
    }
}
