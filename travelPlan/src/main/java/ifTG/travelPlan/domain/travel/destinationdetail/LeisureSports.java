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
@Table(name = "destination_leisure_sports")
public class LeisureSports {
    @Id
    @Column(name = "destination_leisure_sports_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String capacity;
    private String usageTime;
    private String openPeriod;
    private String parking;
    private String parkingFee;
    private String usageFee;
    private String checkPet;
    private String recommendedAge;
    private String checkBabyStroller;

    @Builder
    public LeisureSports(Destination destination, String capacity, String usageTime, String openPeriod, String parking, String parkingFee, String usageFee, String checkPet, String recommendedAge, String checkBabyStroller) {
        this.destination = destination;
        this.capacity = capacity;
        this.usageTime = usageTime;
        this.openPeriod = openPeriod;
        this.parking = parking;
        this.parkingFee = parkingFee;
        this.usageFee = usageFee;
        this.checkPet = checkPet;
        this.recommendedAge = recommendedAge;
        this.checkBabyStroller = checkBabyStroller;
    }
}
