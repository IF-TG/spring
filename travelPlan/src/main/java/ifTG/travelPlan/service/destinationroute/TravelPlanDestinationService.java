package ifTG.travelPlan.service.destinationroute;


import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;

import java.util.List;


public interface TravelPlanDestinationService {
    List<TravelPlanDestinationDto> getDestinationRouteByTravelPlanId(Long travelPlanId);

    List<TravelPlanDestinationDto> addDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto);

    List<TravelPlanDestinationDto> updateDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto);

}
