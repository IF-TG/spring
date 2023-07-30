package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.user.ScrapFolder;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "destination_scraps")
public class DestinationScrap {
    @EmbeddedId
    private DestinationScrapId destinationLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="destination_id", nullable = false, insertable = false, updatable = false)
    private Destination destination;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "folder_id", referencedColumnName = "scrap_folder_id", insertable = false, updatable = false)
    private ScrapFolder scrapFolder;
}
