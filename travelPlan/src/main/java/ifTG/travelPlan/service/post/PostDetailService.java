package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.controller.dto.RequestCommentByPostDto;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;
import org.springframework.data.domain.Pageable;


public interface PostDetailService {

    PostDetailsWithIsScraped getPostDetail(Long postId, Long userId, Pageable pageable);

    PostDetailsWithIsScraped getPostDetail(Long postId, Pageable pageable);
}
