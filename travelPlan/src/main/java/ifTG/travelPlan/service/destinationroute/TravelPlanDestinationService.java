package ifTG.travelPlan.service.destinationroute;


import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;

import java.util.List;


public interface TravelPlanDestinationService {
    List<TravelPlanDestinationDto> getDestinationRouteByTravelPlanId(Long userId, Long travelPlanId);

    List<TravelPlanDestinationDto> addDestinationToTravelPlan(Long userId, DestinationRouteListWithTravelPlanIdDto dto);

    List<TravelPlanDestinationDto> updateDestinationToTravelPlan(Long userId, DestinationRouteListWithTravelPlanIdDto dto);

}
