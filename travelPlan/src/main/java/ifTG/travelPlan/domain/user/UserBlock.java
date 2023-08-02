package ifTG.travelPlan.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "user_blocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBlock {
    @EmbeddedId
    UserBlockId userBlockId;
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    User user;
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "blocked_user_id", insertable=false, updatable=false)
    User blockedUser;

    public UserBlock(UserBlockId userBlockId) {
        this.userBlockId = userBlockId;
    }

    public Long getBlockedUserId(){
        return this.userBlockId.getBlockedUserId();
    }
}
