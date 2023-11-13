package ifTG.travelPlan.dto.destination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CulturalFacilityDto {
    private Long culturalFacilityId;
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
}
