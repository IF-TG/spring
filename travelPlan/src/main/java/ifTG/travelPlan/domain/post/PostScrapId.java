package ifTG.travelPlan.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostScrapId {
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    public PostScrapId(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
