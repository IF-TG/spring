package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Walking extends DestinationRoute {
    @Column(nullable = false)
    private float distance;

    public Walking(DestinationRouteId destinationRouteId) {
        super(destinationRouteId);
    }
}
