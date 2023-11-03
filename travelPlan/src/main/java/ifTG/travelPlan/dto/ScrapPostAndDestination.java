package ifTG.travelPlan.dto;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ScrapPostAndDestination {
    private final Long userId;
    private final List<DestinationDto> destinationList;
    private final List<PostDto> postList;
}
