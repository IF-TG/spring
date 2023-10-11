package ifTG.travelPlan.domain.travel.destinationdetail;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
