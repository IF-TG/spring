package ifTG.travelPlan.controller.destinationroute;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;
import ifTG.travelPlan.service.destinationroute.TravelPlanDestinationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination/route")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class TravelPlanDestinationController {
    private final TravelPlanDestinationService travelPlanDestinationService;

    @GetMapping
    public ResponseEntity<Result<List<TravelPlanDestinationDto>>> getDestinationRouteByTravelPlanId(@AuthenticationUser Long userId, @RequestParam Long travelPlanId){
        return Result.isSuccess(travelPlanDestinationService.getDestinationRouteByTravelPlanId(userId, travelPlanId));
    }

    @PostMapping
    public ResponseEntity<Result<List<TravelPlanDestinationDto>>> saveDestinationToTravelPlan(@AuthenticationUser Long userId, @RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return Result.isSuccess(travelPlanDestinationService.addDestinationToTravelPlan(userId, dto));

    }

    @PutMapping
    public ResponseEntity<Result<List<TravelPlanDestinationDto>>> updateDestinationToTravelPlanById(@AuthenticationUser Long userId, @RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return Result.isSuccess(travelPlanDestinationService.updateDestinationToTravelPlan(userId, dto));
    }

}
