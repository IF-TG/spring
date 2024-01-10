package ifTG.travelPlan.repository.springdata.comment;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * select all
     */
    List<Comment> findAllByUser(User user);
    List<Comment> findAllByPost(Post post);

    /**
     * select all with fetch join
     */
    @Query(value = "SELECT c FROM Comment c WHERE c.post.id = :id ORDER BY c.createdAt",
        countQuery = "SELECT COUNT(c) FROM Comment c WHERE c.post.id = :id")
    Page<Comment> findAllByPostId(Long id, Pageable pageable);

    @Query(value = "SELECT c FROM Comment c LEFT JOIN FETCH c.user u WHERE c.post.id = :postId ORDER BY c.createdAt ASC",
        countQuery = "SELECT COUNT(c) FROM Comment c WHERE c.post.id = :postId")
    Page<Comment> findAllWithNestedCommentWithUserByPostId(Pageable pageable, @Param("postId") Long postId);

    Optional<Comment> findWithNestedCommentById(Long commentId);

    /**
     * delete
     */
    @Query("DELETE FROM Comment c WHERE c.nestedCommentList IS EMPTY AND c.isDeleted = true")
    @Modifying
    void deleteCommentWithSoftDeletedNestedComments(Long nestedCommentId);
}
