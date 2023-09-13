package ifTG.travelPlan.service.destinationroute;


import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;

import java.util.List;


public interface TravelPlanDestinationService {
    List<TravelPlanDestinationDto> getDestinationRouteByTravelPlanId(TravelPlanIdDto travelPlanId);

    List<TravelPlanDestinationDto> addDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto);

    List<TravelPlanDestinationDto> updateDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto);

}
