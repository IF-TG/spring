package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {


    boolean existsByTourApiContentId(Long tourApiContentId);
}
