package ifTG.travelPlan.controller.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DestinationRouteListWithTravelPlanIdDto {
    private Long travelPlanId;
    private final List<TravelPlanDestinationIdDto> travelPlan = new ArrayList<>();
}
