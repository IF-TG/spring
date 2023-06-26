package ifTG.travelPlan.repository.springdata.travel.destinationroute;

import ifTG.travelPlan.domain.travel.destinationroute.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
