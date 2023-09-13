package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DestinationRouteListWithTravelPlanIdDto {
    private Long travelPlanId;
    private final List<TravelPlanDestinationDto> data = new ArrayList<>();
}
