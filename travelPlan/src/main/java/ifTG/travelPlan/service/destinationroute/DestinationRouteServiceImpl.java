package ifTG.travelPlan.domain.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;
import ifTG.travelPlan.domain.travel.TravelPlanDestinationRoute;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanDestinationRouteRepository;

import java.util.List;

public class DestinationRouteServiceImpl implements DestinationRouteService{
    private TravelPlanDestinationRouteRepository travelPlanDestinationRouteRepository;


    @Override
    public List<DestinationRouteDto> getDestinationRouteByTravelPlanId(TravelPlanIdDto travelPlanId) {
        List<TravelPlanDestinationRoute> travelPlanDestinationRouteList = travelPlanDestinationRouteRepository.findWithTravelPlanAndDestinationRouteById(travelPlanId.getTravelPlanId());
        return null;
    }
}
