package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "city_buses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CityBus extends DestinationRoute{

    @Column(nullable = false)
    private String busNumber;

    @Column(nullable = false)
    private String startPoint;

    @Column(nullable = false)
    private String endPoint;

    @Column(nullable = false)
    private int distance;

    @Column(nullable = false)
    private int averageTime;

    @Column(nullable = false)
    private int charge;

    public CityBus(DestinationRouteId destinationRouteId) {
        super(destinationRouteId);
    }
}
