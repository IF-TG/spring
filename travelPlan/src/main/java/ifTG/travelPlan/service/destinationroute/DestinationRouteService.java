package ifTG.travelPlan.service.destinationroute;


import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.dto.travel.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;

import java.util.List;


public interface DestinationRouteService {
    List<DestinationRouteDto> getDestinationRouteByTravelPlanId(TravelPlanIdDto travelPlanId);

    DestinationDto addDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto);
}
