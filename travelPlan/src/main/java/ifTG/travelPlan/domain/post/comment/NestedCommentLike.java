package ifTG.travelPlan.domain.post.comment;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NestedCommentLike {
    @EmbeddedId
    NestedCommentLikeId nestedCommentLikeId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "nested_comment_id", insertable = false, updatable = false)
    private NestedComment nestedComment;

    public NestedCommentLike(NestedCommentLikeId nestedCommentLikeId) {
        this.nestedCommentLikeId = nestedCommentLikeId;
    }
}
