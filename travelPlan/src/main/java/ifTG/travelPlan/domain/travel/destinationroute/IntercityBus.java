package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class IntercityBus extends DestinationRoute {


    @Column(length = 50, nullable = false)
    private String expressBusCompany;

    @Column(nullable = false)
    private String startPoint;

    @Column(nullable = false)
    private String endPoint;

    @Column(nullable = false)
    private LocalDateTime startDt;

    @Column(nullable = false)
    private LocalDateTime endDt;

    @Column(nullable = false)
    private float distance;

    private Integer charge;

    public IntercityBus(DestinationRouteId destinationRouteId) {
        super(destinationRouteId);
    }
}
