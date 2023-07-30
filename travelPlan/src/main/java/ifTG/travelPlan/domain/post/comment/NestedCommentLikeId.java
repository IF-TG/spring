package ifTG.travelPlan.domain.post.comment;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter @AllArgsConstructor
public class NestedCommentLikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nested_comment_id")
    private Long nestedCommentId;
}
