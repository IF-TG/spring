package ifTG.travelPlan.domain.travel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DestinationScrapId implements Serializable {
    @Column(name = "destination_id")
    private String destinationId;

    @Column(name = "destination_type")
    private String destinationType;

    @Column(name = "folder_id")
    private Long folderId;
}
