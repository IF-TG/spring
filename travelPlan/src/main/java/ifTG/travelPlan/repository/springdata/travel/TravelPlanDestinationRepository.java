package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.TravelPlanDestination;
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
public interface TravelPlanDestinationRepository extends JpaRepository<TravelPlanDestination, Long> {
    /**
     * select all
     */
    Page<TravelPlanDestination> findAll(Pageable pageable);

    @Query("SELECT tpd FROM TravelPlanDestination tpd " +
            "JOIN FETCH tpd.travelPlan tp " +
            "JOIN FETCH tpd.destination d " +
            "WHERE tp.id = :id " +
            "ORDER BY tpd.eta")
    List<TravelPlanDestination> findWithTravelPlanAndDestinationRouteByTravelPlanId(@Param("id") Long id);

}
