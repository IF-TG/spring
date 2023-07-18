package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Ship extends DestinationRoute {
    @Column(nullable = false)
    private String shipName;
    @Column(nullable = false)
    private String startPoint;
    @Column(nullable = false)
    private String endPoint;
    @Column(nullable = false)
    private LocalDateTime startDt;
    @Column(nullable = false)
    private LocalDateTime endDt;
    private Integer charge;

    public Ship(DestinationRouteId destinationRouteId) {
        super(destinationRouteId);
    }
}
