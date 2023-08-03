package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.post.comment.CommentLike;
import ifTG.travelPlan.domain.post.comment.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

}
