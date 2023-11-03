package ifTG.travelPlan.controller.travelplan;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.service.travelplan.TravelPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelPlan")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    /*@GetMapping
    public Result<List<TravelPlanDto>> getTravelPlanListByUserId(@RequestBody RequestTravelPlanListDto requestTravelPlanListDto){
        return new Result<>(travelPlanService.getTravelPlanByUserId(requestTravelPlanListDto));
    }

    @PostMapping
    public TravelPlanDto saveTravelPlan(@RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return travelPlanService.saveTravelPlan(requestTravelPlanDto);
    }

    @PutMapping
    public TravelPlanDto updateTravelPlan(@RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return travelPlanService.updateTravelPlan(requestTravelPlanDto);
    }

    @DeleteMapping
    public Boolean deleteTravelPlan(@RequestBody TravelPlanIdDto travelPlanIdDto){
        return travelPlanService.deleteTravelPlan(travelPlanIdDto);
    }*/

}
