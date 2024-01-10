package ifTG.travelPlan.repository.springdata.travel.sucategory;

import ifTG.travelPlan.domain.travel.destinationdetail.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
