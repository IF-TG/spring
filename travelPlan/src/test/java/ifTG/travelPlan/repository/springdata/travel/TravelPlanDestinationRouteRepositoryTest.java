package ifTG.travelPlan.repository.springdata.travel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravelPlanDestinationRouteRepositoryTest {
    @Autowired
    TravelPlanDestinationRouteRepository travelPlanDestinationRouteRepository;

    @Test
    void findWithTravelPlanAndDestinationRouteById() {
        travelPlanDestinationRouteRepository.findWithTravelPlanAndDestinationRouteById(0L);
    }
}