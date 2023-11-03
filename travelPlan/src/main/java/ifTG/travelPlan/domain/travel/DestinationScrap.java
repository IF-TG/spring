package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "destination_scraps")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DestinationScrap {
    @EmbeddedId
    private DestinationScrapId destinationLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="destination_id", nullable = false, insertable = false, updatable = false)
    private Destination destination;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(nullable = false)
    private String folderName;

    public DestinationScrap updateFolderName(String folderName){
        this.folderName = folderName;
        return this;
    }
    public DestinationScrap(DestinationScrapId destinationLikeId, String folderName) {
        this.destinationLikeId = destinationLikeId;
        this.folderName = folderName;
    }
}
