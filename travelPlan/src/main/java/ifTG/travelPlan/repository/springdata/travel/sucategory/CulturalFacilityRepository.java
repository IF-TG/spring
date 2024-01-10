package ifTG.travelPlan.repository.springdata.travel.sucategory;

import ifTG.travelPlan.domain.travel.destinationdetail.CulturalFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulturalFacilityRepository extends JpaRepository<CulturalFacility, Long> {
}
