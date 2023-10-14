package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.destinationdetail.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
