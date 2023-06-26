package ifTG.travelPlan.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class CommentLikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_id")
    private Long commentId;
}
