package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Table(name = "post_likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike {
    @EmbeddedId
    private PostLikeId postLikeId;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "post_id", insertable=false, updatable=false)
    private Post post;

}
