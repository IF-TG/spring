package ifTG.travelPlan.domain.destinationroute;


import ifTG.travelPlan.controller.dto.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;

import java.util.List;

public interface DestinationRouteService {
    List<DestinationRouteDto> getDestinationRouteByTravelPlanId(TravelPlanIdDto travelPlanId);
}
