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
@Table(name = "destination_shopping")
public class Shopping {
    @Id
    @Column(name = "destination_shopping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String checkBabyStroller;
    private String checkPet;
    private String openDate;
    private String openTime;
    private String restDate;
    private String fairDate;
    private String saleItem;
    private String scale;

    @Builder
    public Shopping(Destination destination, String checkBabyStroller, String checkPet, String openDate, String openTime, String restDate, String fairDate, String saleItem, String scale) {
        this.destination = destination;
        this.checkBabyStroller = checkBabyStroller;
        this.checkPet = checkPet;
        this.openDate = openDate;
        this.openTime = openTime;
        this.restDate = restDate;
        this.fairDate = fairDate;
        this.saleItem = saleItem;
        this.scale = scale;
    }
}
