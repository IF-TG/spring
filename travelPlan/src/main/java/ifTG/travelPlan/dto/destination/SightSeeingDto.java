package ifTG.travelPlan.dto.destination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SightSeeingDto {
    private Long sightSeeingId;
    private String capacity;
    private String experienceGuide;
    private String checkBabyStroller;
    private String restDate;
    private String usageTime;
    private String openDate;
    private String checkPet;
}
