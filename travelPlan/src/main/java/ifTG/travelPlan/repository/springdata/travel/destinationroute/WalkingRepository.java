package ifTG.travelPlan.repository.springdata.travel.destinationroute;

import ifTG.travelPlan.domain.travel.destinationroute.Walking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkingRepository extends JpaRepository<Walking, Long> {
}
