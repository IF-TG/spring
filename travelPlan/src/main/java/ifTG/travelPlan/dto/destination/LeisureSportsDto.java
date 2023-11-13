package ifTG.travelPlan.dto.destination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LeisureSportsDto {
    private Long leisureSportId;
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
