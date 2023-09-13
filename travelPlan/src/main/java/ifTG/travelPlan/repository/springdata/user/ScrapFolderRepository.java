package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.ScrapFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapFolderRepository extends JpaRepository<ScrapFolder, Long> {

    @Query("SELECT sf FROM ScrapFolder sf JOIN FETCH sf.scrapFolderImgList WHERE sf.user.id = :userId")
    List<ScrapFolder> findAllWithScrapImgListByUserId(Long userId);
}
