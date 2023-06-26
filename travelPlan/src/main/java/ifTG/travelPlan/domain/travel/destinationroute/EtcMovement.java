package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class EtcMovement extends DestinationRoute {
    @Column(nullable = false)
    private String movementName;
    private float distance;
    private String startPoint;
    private String endPoint;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private Integer charge;

}
