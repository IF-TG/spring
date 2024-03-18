package ifTG.travelPlan.controller.travelplan;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.service.travelplan.TravelPlanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelPlan")
@SecurityRequirement(name = "Authorization")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @GetMapping
    public ResponseEntity<Result<List<TravelPlanDto>>> getTravelPlanListByUserId(@AuthenticationUser Long userId){
        return Result.isSuccess(travelPlanService.getTravelPlanByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Result<TravelPlanDto>> saveTravelPlan(@AuthenticationUser Long userId, @RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return Result.isSuccess(travelPlanService.saveTravelPlan(userId, requestTravelPlanDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<TravelPlanDto>> updateTravelPlan(@AuthenticationUser Long userId, @PathVariable(name = "id") Long travelPlanId, @RequestBody RequestTravelPlanDto requestTravelPlanDto){
        return Result.isSuccess(travelPlanService.updateTravelPlan(userId, travelPlanId, requestTravelPlanDto));
    }

    @DeleteMapping
    public ResponseEntity<Result<Boolean>> deleteTravelPlan(@AuthenticationUser Long userId, @RequestParam Long travelPlanId){
        return Result.isSuccess(travelPlanService.deleteTravelPlan(userId, travelPlanId));
    }
}
