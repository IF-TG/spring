package ifTG.travelPlan.repository.jdbc;

import ifTG.travelPlan.domain.travel.TravelPlanDestination;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcDestinationRouteRepositoryImpl implements JdbcDestinationRouteRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insertTravelPlanDestination(List<TravelPlanDestination> travelPlanDestinationList){
        String sql = "INSERT INTO travel_plan_destination (travel_plan_id, destination_id, eta) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, travelPlanDestinationList, travelPlanDestinationList.size(),
                (PreparedStatement ps, TravelPlanDestination travelPlanDestination)->{
                    ps.setLong(1, travelPlanDestination.getTravelPlan().getId());
                    ps.setLong(2, travelPlanDestination.getDestination().getId());
                    ps.setTimestamp(3, Timestamp.valueOf(travelPlanDestination.getEta()));
                }
                );
    }
}
