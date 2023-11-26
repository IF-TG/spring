package ifTG.travelPlan.dto.destination;

import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseERecommendDestinationDto {
    private String title;
    private List<ResponseEDestinationDto> destination;
}
