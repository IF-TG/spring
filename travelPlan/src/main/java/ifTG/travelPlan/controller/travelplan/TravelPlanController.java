package ifTG.travelPlan.controller.travelplan;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.service.travelplan.TravelPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelPlan")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @GetMapping
    public Result<List<TravelPlanDto>> getTravelPlanListByUserId(@RequestParam Long userId){
        return new Result<>(travelPlanService.getTravelPlanByUserId(userId));
    }

    @PostMapping
    public TravelPlanDto saveTravelPlan(@RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return travelPlanService.saveTravelPlan(requestTravelPlanDto);
    }

    @PutMapping("/{id}")
    public TravelPlanDto updateTravelPlan(@PathVariable(name = "id") Long travelPlanId, @RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return travelPlanService.updateTravelPlan(travelPlanId, requestTravelPlanDto);
    }

    @DeleteMapping
    public Boolean deleteTravelPlan(@RequestParam Long travelPlanId){
        return travelPlanService.deleteTravelPlan(travelPlanId);
    }

}
