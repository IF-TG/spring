package ifTG.travelPlan.repository.springdata.comment;

import ifTG.travelPlan.domain.post.comment.CommentLike;
import ifTG.travelPlan.domain.post.comment.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

    @Modifying
    @Query("DELETE FROM CommentLike cl WHERE cl.comment.id = :commentId")
    void deleteByCommentId(Long commentId);
}
