package ifTG.travelPlan.controller.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;
import ifTG.travelPlan.service.destinationroute.TravelPlanDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinationRoute")
@RequiredArgsConstructor
public class TravelPlanDestinationController {
    private final TravelPlanDestinationService travelPlanDestinationService;

    @GetMapping
    public ResponseEntity<Result<List<TravelPlanDestinationDto>>> getDestinationRouteByTravelPlanId(@RequestParam Long travelPlanId){
        return Result.isSuccess(travelPlanDestinationService.getDestinationRouteByTravelPlanId(travelPlanId));
    }

    @PostMapping
    public ResponseEntity<Result<List<TravelPlanDestinationDto>>> addDestinationToTravelPlan(@RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return Result.isSuccess(travelPlanDestinationService.addDestinationToTravelPlan(dto));

    }

    @PutMapping
    public ResponseEntity<Result<List<TravelPlanDestinationDto>>> updateDestinationToTravelPlanById(@RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return Result.isSuccess(travelPlanDestinationService.updateDestinationToTravelPlan(dto));
    }

}
