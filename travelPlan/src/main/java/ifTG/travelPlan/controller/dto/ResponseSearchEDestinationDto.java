package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseSearchEDestinationDto {
    private List<ResponseEDestinationDto> destinations;
    private boolean isGptRelated;
}
