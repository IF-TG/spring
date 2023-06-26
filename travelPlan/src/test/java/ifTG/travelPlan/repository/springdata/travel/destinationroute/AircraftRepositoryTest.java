package ifTG.travelPlan.repository.springdata.travel.destinationroute;

import ifTG.travelPlan.domain.travel.destinationroute.Aircraft;
import ifTG.travelPlan.domain.travel.destinationroute.DestinationRouteId;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class AircraftRepositoryTest {
    @Autowired AircraftRepository aircraftRepository;

    @Test
    public void findById(){
        log.info("findById start ");
        Aircraft aircraft = Aircraft.builder()
                .id("1")
                .type("aircraft")
                .airlineName("1")
                .startPoint("지구 어딘가")
                .endPoint("지구 어딘가")
                .startDt(LocalDateTime.now())
                .endDt(LocalDateTime.now())
                .distance(11.3)
                .charge(1).build();
        aircraftRepository.save(aircraft);

        Assertions.assertThat(aircraftRepository.findById(new DestinationRouteId("1", "aircraft")).get().getAirlineName()).isSameAs(aircraft.getAirlineName());
    }
}