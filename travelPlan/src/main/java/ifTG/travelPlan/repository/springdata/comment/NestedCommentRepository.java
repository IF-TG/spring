package ifTG.travelPlan.repository.springdata.comment;

import ifTG.travelPlan.domain.post.comment.NestedComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NestedCommentRepository extends JpaRepository<NestedComment, Long> {
}
