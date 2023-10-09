package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.ScrapFolder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "post_scraps")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostScrap {
    @EmbeddedId
    private PostScrapId postScrapId;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false, insertable=false, updatable=false)
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "folder_id", referencedColumnName = "scrap_folder_id", insertable = false, updatable = false)
    private ScrapFolder scrapFolder;

    @Column(nullable = true)
    private String thumbnail;
}
