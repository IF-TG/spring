package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    @Query("SELECT pi FROM PostImg pi WHERE pi.post.id = :postId")
    List<PostImg> findAllByPostId(Long postId);

   /* @Query("SELECT pi FROM PostImg pi WHERE pi.post.id IN :postIdList WHERE pi.isThumbnail = true")
    List<PostImg> findAllIsThumbnailByPostImgList(List<Long> postIdList);*/

    @Query("SELECT pi FROM PostImg pi " +
            "WHERE pi.post.id IN (SELECT ps.post.id FROM PostScrap ps WHERE ps.scrapFolder.user.id = :userId) AND " +
            "pi.isThumbnail = true")
    List<PostImg> findAllIsThumbnailListByUserId(Long userId);
    /**
     * 파일 다운로드 추가 예정
     */
}
