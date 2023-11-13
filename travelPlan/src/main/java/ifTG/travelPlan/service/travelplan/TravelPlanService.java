package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.controller.dto.RequestTravelPlanDto;
import ifTG.travelPlan.controller.dto.TravelPlanDto;

import java.util.List;

public interface TravelPlanService {

    TravelPlanDto saveTravelPlan(RequestTravelPlanDto requestTravelPlanDto);

    TravelPlanDto updateTravelPlan(Long travelPlanId, RequestTravelPlanDto requestTravelPlanDto);

    Boolean deleteTravelPlan(Long travelPlanId);

    List<TravelPlanDto> getTravelPlanByUserId(Long userId);
}
