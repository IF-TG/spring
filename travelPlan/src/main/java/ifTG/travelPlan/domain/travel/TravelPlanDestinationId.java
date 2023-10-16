package ifTG.travelPlan.domain.travel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlanDestinationId implements Serializable {
    @Column(name = "travel_plan_id")
    private Long travelPlanId;

    @Column(name = "destination_id")
    private Long destinationId;

    public TravelPlanDestinationId(Long travelPlanId, Long destinationId) {
        this.travelPlanId = travelPlanId;
        this.destinationId = destinationId;
    }

}
