package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.controller.dto.RequestTravelPlanDto;
import ifTG.travelPlan.controller.dto.RequestTravelPlanListDto;
import ifTG.travelPlan.controller.dto.TravelPlanDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;

import java.util.List;

public interface TravelPlanService {

    TravelPlanDto saveTravelPlan(RequestTravelPlanDto requestTravelPlanDto);

    TravelPlanDto updateTravelPlan(RequestTravelPlanDto requestTravelPlanDto);

    Boolean deleteTravelPlan(TravelPlanIdDto travelPlanIdDto);

    List<TravelPlanDto> getTravelPlanByUserId(RequestTravelPlanListDto requestTravelPlanListDto);
}
