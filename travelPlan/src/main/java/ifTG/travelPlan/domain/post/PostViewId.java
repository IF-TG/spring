package ifTG.travelPlan.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostViewId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;
}
