package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "post_likes")
@Getter
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

    public PostLike(PostLikeId postLikeId) {
        this.postLikeId = postLikeId;
    }

    public Long getPostLikedId(){
        return this.postLikeId.getPostId();
    }
}
