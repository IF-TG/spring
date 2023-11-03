package ifTG.travelPlan.service.post;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostView;
import ifTG.travelPlan.domain.post.PostViewId;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.post.PostViewRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostViewServiceImpl implements PostViewService{
    private final PostViewRepository postViewRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addPostViewByPostIdAndUserId(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new NotFoundException("not found post"));
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("not found user"));
        PostView postView = new PostView(user, post);
        postViewRepository.save(postView);
    }
}
