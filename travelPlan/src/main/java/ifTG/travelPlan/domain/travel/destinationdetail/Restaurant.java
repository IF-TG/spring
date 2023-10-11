package ifTG.travelPlan.domain.travel.destinationdetail;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
