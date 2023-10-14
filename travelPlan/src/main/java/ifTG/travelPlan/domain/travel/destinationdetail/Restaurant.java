package ifTG.travelPlan.domain.travel.destinationdetail;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "destination_restaurant")
public class Restaurant {
    @Id
    @Column(name = "destination_restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String featuredMenu;
    private String treatMenu;
    private String openDate;
    private String openTime;
    private String packing;
    private String parking;
    private String restDate;
    private String seat;
    private String scale;

    @Builder
    public Restaurant(Destination destination, String featuredMenu, String treatMenu, String openDate, String openTime, String packing, String parking, String restDate, String seat, String scale) {
        this.destination = destination;
        this.featuredMenu = featuredMenu;
        this.treatMenu = treatMenu;
        this.openDate = openDate;
        this.openTime = openTime;
        this.packing = packing;
        this.parking = parking;
        this.restDate = restDate;
        this.seat = seat;
        this.scale = scale;
    }
}
