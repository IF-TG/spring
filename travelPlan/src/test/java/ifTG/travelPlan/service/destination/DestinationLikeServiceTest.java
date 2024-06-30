package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.DestinationLikeId;
import ifTG.travelPlan.dto.post.ToggleDto;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DestinationLikeServiceTest {
    @Autowired private DestinationLikeService destinationLikeService;
    @Test
    public void destinationLike(){
        ToggleDto toggleA = destinationLikeService.toggleLike(1L, 1L);
        ToggleDto toggleB = destinationLikeService.toggleLike(1L, 1L);

        assertThat(toggleA.getValue()).isEqualTo(true);
        assertThat(toggleB.getValue()).isEqualTo(false);
    }
}