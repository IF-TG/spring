package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostLikeId;
import ifTG.travelPlan.domain.post.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, PostLikeId> {
}
