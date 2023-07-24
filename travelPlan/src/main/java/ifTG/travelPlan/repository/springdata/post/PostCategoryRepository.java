package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    @Query("DELETE FROM PostCategory pc WHERE pc.post.id = :id")
    @Modifying
    public void deleteAllByPostId(Long id);
}
