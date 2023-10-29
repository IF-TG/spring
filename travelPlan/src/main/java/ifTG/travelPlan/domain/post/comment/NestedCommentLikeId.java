package ifTG.travelPlan.domain.post.comment;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter @AllArgsConstructor
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class NestedCommentLikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nested_comment_id")
    private Long nestedCommentId;
}
