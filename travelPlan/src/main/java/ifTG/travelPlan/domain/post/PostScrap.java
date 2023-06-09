package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.ScrapFolder;
import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "post_scraps")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostScrap {
    @EmbeddedId
    private PostScrapId postLikeId;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false, insertable=false, updatable=false)
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "folder_id", referencedColumnName = "scrap_folder_id", insertable = false, updatable = false)
    private ScrapFolder scrapFolder;
}
