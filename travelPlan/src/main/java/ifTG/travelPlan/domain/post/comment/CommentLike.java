package ifTG.travelPlan.domain.post.comment;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name= "comment_likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {
    @EmbeddedId
    private CommentLikeId commentLikeId;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "comment_id", insertable=false, updatable=false)
    private Comment comment;

    public CommentLike(CommentLikeId commentLikeId) {
        this.commentLikeId = commentLikeId;
    }
}
