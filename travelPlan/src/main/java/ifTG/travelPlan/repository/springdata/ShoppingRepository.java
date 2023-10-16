package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.travel.destinationdetail.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
