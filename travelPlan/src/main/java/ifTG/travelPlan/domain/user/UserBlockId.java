package ifTG.travelPlan.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserBlockId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "blocked_user_id")
    private Long blockedUserId;
}
