package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.travel.DestinationScrapId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationScrapRepository extends JpaRepository<DestinationScrap, DestinationScrapId> {
    @Query("SELECT ds FROM DestinationScrap ds JOIN FETCH ds.destination d WHERE ds.user.id = :userId")
    List<DestinationScrap> findAllWithDestinationByUserId(Long userId);

    Slice<DestinationScrap> findAllWithDestinationByFolderNameAndUserId(String folderName, Long userId, Pageable pageable);
}
