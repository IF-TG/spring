package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.post.PostScrapId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostScrapRepository extends JpaRepository<PostScrap, PostScrapId> {
    @Query("SELECT ps FROM PostScrap ps WHERE ps.user.id= :userId")
    List<PostScrap> findAllByUserId(Long userId);

    Slice<PostScrap> findAllWithPostByFolderNameAndUserId(String folderName, Long userId, Pageable pageable);

  /*  @Query("SELECT ps FROM PostScrap ps JOIN FETCH ps.post p JOIN FETCH ps.scrapFolder sf WHERE sf.userId = :userId")
    List<PostScrap> findAllWithPostAndScrapFolderByUserId(Long userId);*/
}
