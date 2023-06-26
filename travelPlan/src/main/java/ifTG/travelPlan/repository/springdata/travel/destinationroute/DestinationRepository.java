package ifTG.travelPlan.repository.springdata.travel.destinationroute;

import ifTG.travelPlan.domain.travel.destinationroute.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

}
