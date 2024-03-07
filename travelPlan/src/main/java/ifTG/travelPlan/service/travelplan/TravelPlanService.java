package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.controller.dto.RequestTravelPlanDto;
import ifTG.travelPlan.controller.dto.TravelPlanDto;

import java.util.List;

public interface TravelPlanService {

    TravelPlanDto saveTravelPlan(Long userId, RequestTravelPlanDto requestTravelPlanDto);

    TravelPlanDto updateTravelPlan(Long userId, Long travelPlanId, RequestTravelPlanDto requestTravelPlanDto);

    Boolean deleteTravelPlan(Long userID, Long travelPlanId);

    List<TravelPlanDto> getTravelPlanByUserId(Long userId);
}
