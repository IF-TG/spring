package ifTG.travelPlan.repository.springdata.travel;

import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
    /**
     * select all
     */
    Page<TravelPlan> findAll(Pageable pageable);
    List<TravelPlan> findAllByUser(User user);
    @Query("SELECT tp FROM TravelPlan tp JOIN FETCH tp.travelPlanDestinationList tpd WHERE tp.id = :id")
    List<TravelPlan> findAllWithTravelPlanDestinationById(Long id);

    @Query("SELECT tp FROM TravelPlan tp WHERE tp.user = :userId")
    List<TravelPlan> findAllByUserId(Long userId);
}
