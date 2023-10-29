package ifTG.travelPlan.domain.travel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class DestinationScrapId implements Serializable {
    @Column(name = "destination_id")
    private Long destinationId;

    @Column(name = "user_id")
    private Long userId;

    public DestinationScrapId(Long destinationId, Long userId) {
        this.destinationId = destinationId;
        this.userId = userId;
    }
}
