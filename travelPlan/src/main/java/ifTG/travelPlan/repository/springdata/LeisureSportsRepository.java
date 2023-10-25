package ifTG.travelPlan.repository.springdata;

import ifTG.travelPlan.domain.travel.destinationdetail.LeisureSports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureSportsRepository extends JpaRepository<LeisureSports, Long> {
}
