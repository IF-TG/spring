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
@Table(name = "destination_cultural_facility")
public class CulturalFacility {
    @Id @Column(name = "destination_cultural_facility_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String capacity;
    private String scale;
    private String usageFee;
    private String usageTime;
    private String spendTime;
    private String checkPet;
    private String checkBabyStroller;
    private String discountInfo;
    private String parking;
    private String parkingFee;

    @Builder
    public CulturalFacility(Destination destination, String capacity, String scale, String usageFee, String usageTime, String spendTime, String checkPet, String checkBabyStroller, String discountInfo, String parking, String parkingFee) {
        this.destination = destination;
        this.capacity = capacity;
        this.scale = scale;
        this.usageFee = usageFee;
        this.usageTime = usageTime;
        this.spendTime = spendTime;
        this.checkPet = checkPet;
        this.checkBabyStroller = checkBabyStroller;
        this.discountInfo = discountInfo;
        this.parking = parking;
        this.parkingFee = parkingFee;
    }
}
