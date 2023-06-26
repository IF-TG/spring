package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.Comment;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
