package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.DestinationLike;
import ifTG.travelPlan.domain.travel.DestinationLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationLikeRepository extends JpaRepository<DestinationLike, DestinationLikeId> {
}
