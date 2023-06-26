package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

/**
 * api 요구사항에 따라 변경 예정
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "destinations")
public class Destination extends DestinationRoute{
    private String destinationName;
    private String location;

    @Formula("(SELECT COUNT(1) FROM destination_likes d WHERE d.destinationId = id AND d.type = type)")
    private Integer likes;
    public Destination(DestinationRouteId destinationRouteId) {
        super(destinationRouteId);
    }
}
