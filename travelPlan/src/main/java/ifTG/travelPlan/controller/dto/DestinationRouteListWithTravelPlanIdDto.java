package ifTG.travelPlan.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DestinationRouteListWithTravelPlanIdDto {
    private Long travelPlanId;
    private final List<TravelPlanDestinationIdDto> travelPlan = new ArrayList<>();
}
