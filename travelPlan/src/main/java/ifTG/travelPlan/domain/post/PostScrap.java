package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.User;
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
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    private String folderName;

    public PostScrap updateFolderName(String folderName){
        this.folderName = folderName;
        return this;
    }

    public PostScrap(PostScrapId postScrapId, String folderName) {
        this.postScrapId = postScrapId;
        this.folderName = folderName;
    }
}
