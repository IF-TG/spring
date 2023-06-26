package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aircraft extends DestinationRoute {
    @Column(nullable = false)
    private String airlineName;
    @Column(nullable = false)
    private String startPoint;
    @Column(nullable = false)
    private String endPoint;
    @Column(nullable = false)
    private LocalDateTime startDt;
    @Column(nullable = false)
    private LocalDateTime endDt;
    @Column(nullable = false)
    private Double distance;
    private Integer charge;


    @Builder
    public Aircraft(String id, String type, String airlineName, String startPoint, String endPoint, LocalDateTime startDt, LocalDateTime endDt, Double distance, Integer charge) {
        super(new DestinationRouteId(id, type));
        this.airlineName = airlineName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDt = startDt;
        this.endDt = endDt;
        this.distance = distance;
        this.charge = charge;
    }

}
