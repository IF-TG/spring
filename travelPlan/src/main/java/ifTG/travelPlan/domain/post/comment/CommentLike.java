package ifTG.travelPlan.domain.post.comment;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name= "comment_likes")
@Getter
public class CommentLike {
    @EmbeddedId
    private CommentLikeId commentLikeId;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "comment_id", insertable=false, updatable=false)
    private Comment comment;
}
