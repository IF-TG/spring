package ifTG.travelPlan.domain.travel.destinationdetail;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
