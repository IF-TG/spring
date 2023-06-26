package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    /**
     * 파일 다운로드 추가 예정
     */
}
