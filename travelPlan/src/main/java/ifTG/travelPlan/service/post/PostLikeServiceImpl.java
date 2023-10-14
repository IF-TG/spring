package ifTG.travelPlan.service.post;

import ifTG.travelPlan.aop.ToggleLike;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostLikeServiceImpl implements PostLikeService{

    @Override
    @ToggleLike
    public ToggleDto toggleLikePost(RequestLikeDto requestLikeDto) {
        return null;
    }
}
