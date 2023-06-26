package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * inheritanceType을 통해
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "destination_routes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DestinationRoute {
    @EmbeddedId
    private DestinationRouteId destinationRouteId;

    public DestinationRoute(DestinationRouteId destinationRouteId) {
        this.destinationRouteId = destinationRouteId;
    }
}
