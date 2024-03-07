package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RequestUpdatePostScrapDto {
    private List<Long> objectIdList;
    private String folderName;
}
