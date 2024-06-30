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
public class DestinationLikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "destination_id")
    private Long destinationId;

    public DestinationLikeId(Long destinationId, Long userId) {
        this.userId = userId;
        this.destinationId = destinationId;
    }
}
