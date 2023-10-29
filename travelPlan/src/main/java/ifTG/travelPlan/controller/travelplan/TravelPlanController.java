package ifTG.travelPlan.controller.travelplan;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.service.travelplan.TravelPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/travelPlan")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @GetMapping
    public Result<List<TravelPlanDto>> getTravelPlanListByUserId(RequestTravelPlanListDto requestTravelPlanListDto){
        return new Result<>(travelPlanService.getTravelPlanByUserId(requestTravelPlanListDto));
    }

    @PostMapping
    public TravelPlanDto saveTravelPlan(RequestTravelPlanDto requestTravelPlanDto){
        return travelPlanService.saveTravelPlan(requestTravelPlanDto);
    }

    @PutMapping
    public TravelPlanDto updateTravelPlan(RequestTravelPlanDto requestTravelPlanDto){
        return travelPlanService.updateTravelPlan(requestTravelPlanDto);
    }

    @DeleteMapping
    public Boolean deleteTravelPlan(TravelPlanIdDto travelPlanIdDto){
        return travelPlanService.deleteTravelPlan(travelPlanIdDto);
    }

}
