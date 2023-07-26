package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * inheritanceType을 통해
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "destination_routes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DestinationRoute {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "destination_route_id")
    private Long id;


}
