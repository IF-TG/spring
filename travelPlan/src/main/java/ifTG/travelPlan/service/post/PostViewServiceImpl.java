package ifTG.travelPlan.service.post;

import ifTG.travelPlan.domain.post.PostView;
import ifTG.travelPlan.domain.post.PostViewId;
import ifTG.travelPlan.repository.springdata.post.PostViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostViewServiceImpl implements PostViewService{
    private final PostViewRepository postViewRepository;


    public void addPostViewByPostIdAndUserId(Long postId, Long userId) {
        PostView postView = new PostView(new PostViewId(userId, postId));
        postViewRepository.save(postView);
    }
}
