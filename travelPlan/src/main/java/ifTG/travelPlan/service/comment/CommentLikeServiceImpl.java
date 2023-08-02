package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.aop.ToggleLike;
import ifTG.travelPlan.controller.post.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentLikeServiceImpl implements CommentLikeService{
    @Override
    @ToggleLike
    public LikeDto toggleLikeComment(RequestLikeDto requestLikeDto) {
        return null;
    }
}
