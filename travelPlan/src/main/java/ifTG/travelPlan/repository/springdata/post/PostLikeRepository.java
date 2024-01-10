package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.post.PostLikeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    @Query("SELECT pl.postLikeId.postId FROM PostLike pl WHERE pl.postLikeId.userId = :userId")
    List<Long> findPostIdByUserId(Long userId);

    Page<PostLike> findAllWithPostByUserId(Pageable pageable, Long userId);
}
