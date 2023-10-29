package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "destination_likes")
public class DestinationLike {
    @EmbeddedId
    private DestinationLikeId destinationLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="destination_id", nullable = false, insertable = false, updatable = false)
    private Destination destination;
}
