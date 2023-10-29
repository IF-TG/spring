package ifTG.travelPlan.repository.springdata.travel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class TravelPlanRepositoryTest {
    @Autowired
    TravelPlanRepository travelPlanRepository;

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 2);
        travelPlanRepository.findAll();
    }

    @Test
    void findAllByUser() {
    }

    @Test
    void findAllWithTravelPlanDestinationRouteById() {
    }
}