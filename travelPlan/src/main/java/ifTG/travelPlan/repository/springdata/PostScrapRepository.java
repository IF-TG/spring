package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.post.PostScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostScrapRepository extends JpaRepository<PostScrap, PostScrapId> {


    @Query("SELECT p.id FROM PostScrap ps JOIN Post p WHERE ps.scrapFolder.user.id = :userId")
    List<Long> findPostIdListByUserIdWithPost(Long userId);

  /*  @Query("SELECT ps FROM PostScrap ps JOIN FETCH ps.post p JOIN FETCH ps.scrapFolder sf WHERE sf.userId = :userId")
    List<PostScrap> findAllWithPostAndScrapFolderByUserId(Long userId);*/
}
