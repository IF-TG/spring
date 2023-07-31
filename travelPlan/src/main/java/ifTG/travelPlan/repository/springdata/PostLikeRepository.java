package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.post.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
}
