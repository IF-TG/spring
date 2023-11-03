package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RequestUpdateDestinationScrapDto {
    private List<Long> objectIdList;
    private Long userId;
    private String folderName;
}
