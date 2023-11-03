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
@Table(name = "destination_sightseeing")
public class SightSeeing {
    @Id @Column(name = "destination_sightseeing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String capacity;

    @Column(length = 500)
    private String experienceGuide;
    private String checkBabyStroller;
    private String restDate;
    private String usageTime;
    private String openDate;
    private String checkPet;

    @Builder
    public SightSeeing(Destination destination, String capacity, String experienceGuide, String checkBabyStroller, String restDate, String usageTime, String openDate, String checkPet) {
        this.destination = destination;
        this.capacity = capacity;
        this.experienceGuide = experienceGuide;
        this.checkBabyStroller = checkBabyStroller;
        this.restDate = restDate;
        this.usageTime = usageTime;
        this.openDate = openDate;
        this.checkPet = checkPet;
    }
}
