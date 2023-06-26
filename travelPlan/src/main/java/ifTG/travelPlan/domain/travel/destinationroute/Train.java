package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Train extends DestinationRoute {
    @Column(nullable = false)
    private String trainName;
    @Column(nullable = false)
    private String trainGrade;
    @Column(nullable = false)
    private String startPoint;

    @Column(nullable = false)
    private String endPoint;

    @Column(nullable = false)
    private LocalDateTime startDt;

    @Column(nullable = false)
    private LocalDateTime endDt;

    private Integer charge;

    public Train(DestinationRouteId destinationRouteId) {
        super(destinationRouteId);
    }
}
