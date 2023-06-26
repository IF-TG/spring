package ifTG.travelPlan.domain.travel.destinationroute;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DestinationRouteId implements Serializable {
    @Column(name = "id")
    private String id;
    private String type;

}
