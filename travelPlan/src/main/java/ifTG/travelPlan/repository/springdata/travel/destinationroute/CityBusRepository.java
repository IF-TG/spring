package ifTG.travelPlan.repository.springdata.travel.destinationroute;

import ifTG.travelPlan.domain.travel.destinationroute.CityBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityBusRepository extends JpaRepository<CityBus, Long> {
}
