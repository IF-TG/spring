package ifTG.travelPlan.domain.travel;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlanDestinationRouteId implements Serializable {
    @Column(name = "travel_plan_id")
    private Long travelPlanId;

    @Column(name = "destination")
    private Long destinationId;
}
