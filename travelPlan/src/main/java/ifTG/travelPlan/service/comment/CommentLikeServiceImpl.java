package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.aop.ToggleLike;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentLikeServiceImpl implements CommentLikeService{
    @Override
    @ToggleLike
    public ToggleDto toggleLikeComment(Long userId, RequestLikeDto requestLikeDto) {
        return null;
    }
}
