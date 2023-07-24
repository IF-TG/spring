package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.post.comment.NestedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NestedCommentRepository extends JpaRepository<NestedComment, Long> {
    @Query("DELETE nc FROM NestedComment nc WHERE nc.id = :id")
    @Modifying
    void deleteById(Long id);
}
