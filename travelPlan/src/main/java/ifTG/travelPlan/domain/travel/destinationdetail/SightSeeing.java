package ifTG.travelPlan.domain.travel.destinationdetail;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "destination_sightseeing")
public class SightSeeing {
    @Id @Column(name = "destination_sightseeing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String capacity;
    private String experienceGuide;
    private String checkBabyStroller;
    private String restDate;
    private String usagePeriod;
    private String usageTime;
    private String openDate;
    private String checkPet;
}
