package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Route extends DestinationRoute{
    private String apiId;
    @Enumerated(value = EnumType.STRING)
    private RouteType roteType;
    private LocalDateTime startDt;
    private String startPoint;
    private LocalDateTime endDt;
    private String endPoint;
    private Float distance;
    private Integer charge;
    private String name;
}