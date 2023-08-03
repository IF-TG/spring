package ifTG.travelPlan.repository.jdbc;
import ifTG.travelPlan.domain.travel.TravelPlanDestination;
import java.util.List;

public interface JdbcDestinationRouteRepository {
    void insertTravelPlanDestination(List<TravelPlanDestination> travelPlanDestinationList);

}
