package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.controller.dto.*;

import java.util.List;

public interface TravelPlanService {

    TravelPlanDto saveTravelPlan(RequestTravelPlanDto requestTravelPlanDto);

    TravelPlanDto updateTravelPlan(RequestTravelPlanDto requestTravelPlanDto);

    Boolean deleteTravelPlan(TravelPlanIdDto travelPlanIdDto);

    List<TravelPlanDto> getTravelPlanByUserId(RequestTravelPlanListDto requestTravelPlanListDto);
}
