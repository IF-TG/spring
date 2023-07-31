package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.post.comment.NestedCommentLike;
import ifTG.travelPlan.domain.post.comment.NestedCommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NestedCommentLikeRepository extends JpaRepository<NestedCommentLike, NestedCommentLikeId> {
}
