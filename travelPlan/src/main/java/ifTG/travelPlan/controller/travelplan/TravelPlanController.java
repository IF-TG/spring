package ifTG.travelPlan.controller.travelplan;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.service.travelplan.TravelPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelPlan")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @GetMapping
    public ResponseEntity<Result<List<TravelPlanDto>>> getTravelPlanListByUserId(@RequestParam Long userId){
        return Result.isSuccess(travelPlanService.getTravelPlanByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Result<TravelPlanDto>> saveTravelPlan(@RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return Result.isSuccess(travelPlanService.saveTravelPlan(requestTravelPlanDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<TravelPlanDto>> updateTravelPlan(@PathVariable(name = "id") Long travelPlanId, @RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return Result.isSuccess(travelPlanService.updateTravelPlan(travelPlanId, requestTravelPlanDto));
    }

    @DeleteMapping
    public ResponseEntity<Result<Boolean>> deleteTravelPlan(@RequestParam Long travelPlanId){
        return Result.isSuccess(travelPlanService.deleteTravelPlan(travelPlanId));
    }

}
