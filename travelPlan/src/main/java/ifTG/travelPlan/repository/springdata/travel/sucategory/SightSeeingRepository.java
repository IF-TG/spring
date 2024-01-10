package ifTG.travelPlan.repository.springdata.travel.sucategory;

import ifTG.travelPlan.domain.travel.destinationdetail.SightSeeing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightSeeingRepository extends JpaRepository<SightSeeing, Long> {
}
