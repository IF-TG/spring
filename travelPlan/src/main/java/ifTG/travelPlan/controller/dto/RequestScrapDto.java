package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestScrapDto {
    private final Long objectId;
    private final Long userId;
    private final String folderName;
}
