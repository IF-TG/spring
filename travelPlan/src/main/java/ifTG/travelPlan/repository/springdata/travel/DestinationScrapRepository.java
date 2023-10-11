package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.travel.DestinationScrapId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationScrapRepository extends JpaRepository<DestinationScrap, DestinationScrapId> {
}
