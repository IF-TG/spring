package ifTG.travelPlan.controller.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;
import ifTG.travelPlan.service.destinationroute.TravelPlanDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinationRoute")
@RequiredArgsConstructor
public class TravelPlanDestinationController {
    private final TravelPlanDestinationService travelPlanDestinationService;

    @GetMapping
    public Result<List<TravelPlanDestinationDto>> getDestinationRouteByTravelPlanId(@RequestParam Long travelPlanId){
        return new Result<>(travelPlanDestinationService.getDestinationRouteByTravelPlanId(travelPlanId));
    }

    @PostMapping
    public Result<List<TravelPlanDestinationDto>> addDestinationToTravelPlan(@RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return new Result<>(travelPlanDestinationService.addDestinationToTravelPlan(dto));
    }

    @PutMapping
    public Result<List<TravelPlanDestinationDto>> updateDestinationToTravelPlanById(@RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return new Result<>(travelPlanDestinationService.updateDestinationToTravelPlan(dto));
    }

}
