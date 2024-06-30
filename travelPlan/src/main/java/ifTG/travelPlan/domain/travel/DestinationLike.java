package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "destination_likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DestinationLike {
    @EmbeddedId
    private DestinationLikeId destinationLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="destination_id", nullable = false, insertable = false, updatable = false)
    private Destination destination;

    public DestinationLike(DestinationLikeId destinationLikeId) {
        this.destinationLikeId = destinationLikeId;
    }
}
