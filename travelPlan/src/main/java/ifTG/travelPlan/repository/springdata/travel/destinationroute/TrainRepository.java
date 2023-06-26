package ifTG.travelPlan.repository.springdata.travel.destinationroute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<ifTG.travelPlan.domain.travel.destinationroute.Train, Long> {
}
