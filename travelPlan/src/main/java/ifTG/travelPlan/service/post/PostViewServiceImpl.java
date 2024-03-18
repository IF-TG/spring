package ifTG.travelPlan.service.post;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostView;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.post.PostViewRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostViewServiceImpl implements PostViewService{
    private final PostViewRepository postViewRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addPostViewByPostIdAndUserId(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        PostView postView = new PostView(user, post);
        postViewRepository.save(postView);
    }
}
