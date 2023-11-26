package ifTG.travelPlan.repository.springdata.travel;



import ifTG.travelPlan.domain.travel.TravelPlanDestination;
import ifTG.travelPlan.domain.travel.TravelPlanDestinationId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlanDestinationRepository extends JpaRepository<TravelPlanDestination, TravelPlanDestinationId> {
    /**
     * select all
     */
    Page<TravelPlanDestination> findAll(Pageable pageable);

    @Query("SELECT tpd FROM TravelPlanDestination tpd " +
            "JOIN FETCH tpd.travelPlan tp " +
            "JOIN FETCH tpd.destination d " +
            "WHERE tpd.travelPlan.id = :id " +
            "ORDER BY tpd.eta")
    List<TravelPlanDestination> findWithTravelPlanAndDestinationRouteByTravelPlanId(@Param("id") Long id);

    List<TravelPlanDestination> findAllByTravelPlanId(Long travelPlanId);
}
