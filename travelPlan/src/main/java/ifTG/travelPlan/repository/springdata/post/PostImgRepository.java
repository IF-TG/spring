package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    @Query("SELECT pi FROM PostImg pi WHERE pi.post.id = :postId")
    List<PostImg> findAllByPostId(Long postId);

    Optional<PostImg> findFirstByPostIdAndIsThumbnail(Long postId, Boolean isThumbnail);

}
