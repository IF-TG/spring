package ifTG.travelPlan.domain.travel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DestinationScrapId implements Serializable {
    @Column(name = "destination_id")
    private Long destinationId;

    @Column(name = "user_id")
    private Long userId;
}
