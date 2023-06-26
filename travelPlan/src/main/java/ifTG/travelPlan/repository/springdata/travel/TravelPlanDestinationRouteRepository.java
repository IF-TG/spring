package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.domain.travel.TravelPlanDestinationRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * joined 전략을 취하는게 옳은 일일까?? - 성능 테스트 예정
 */
@Repository
public interface TravelPlanDestinationRouteRepository extends JpaRepository<TravelPlanDestinationRoute, Long> {
    /**
     * select all
     */
    Page<TravelPlanDestinationRoute> findAll(Pageable pageable);

    @Query("SELECT tpdr FROM TravelPlanDestinationRoute tpdr " +
            "JOIN FETCH tpdr.travelPlan tp " +
            "JOIN FETCH tpdr.destinationRoute dr " +
            "WHERE tp.id = :id")
    List<TravelPlanDestinationRoute> findWithTravelPlanAndDestinationRouteById(@Param("id") Long id);

}
