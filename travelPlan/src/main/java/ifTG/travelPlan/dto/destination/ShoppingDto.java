package ifTG.travelPlan.dto.destination;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ShoppingDto {
    private Long shoppingId;
    private String checkBabyStroller;
    private String checkPet;
    private String openDate;
    private String openTime;
    private String restDate;
    private String fairDate;
    private String saleItem;
    private String scale;

}
