package ifTG.travelPlan.repository.springdata.dairy;

import ifTG.travelPlan.domain.diary.DiaryImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryImgRepository extends JpaRepository<DiaryImg, Long> {

}
