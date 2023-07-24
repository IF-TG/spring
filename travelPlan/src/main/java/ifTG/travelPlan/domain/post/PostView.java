package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "post_likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostView {
    @EmbeddedId
    private PostViewId postViewId;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "post_id", insertable=false, updatable=false)
    private Post post;


    public PostView(PostViewId postViewId) {
        this.postViewId = postViewId;
    }
}
