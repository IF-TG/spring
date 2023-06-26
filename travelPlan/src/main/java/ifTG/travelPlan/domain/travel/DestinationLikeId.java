package ifTG.travelPlan.domain.travel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class DestinationLikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "destination_id")
    private String destinationId;

    @Column(name = "destination_type")
    private String destinationType;
}
