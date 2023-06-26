package ifTG.travelPlan.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PostScrapId {
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "folder_id")
    private Long folderId;
}
