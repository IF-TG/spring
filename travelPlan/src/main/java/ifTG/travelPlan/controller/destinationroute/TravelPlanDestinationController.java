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

@Controller
@RequestMapping("/destinationRoute")
@RequiredArgsConstructor
public class TravelPlanDestinationController {
    private final TravelPlanDestinationService travelPlanDestinationService;

    @GetMapping
    public Result<List<TravelPlanDestinationDto>> getDestinationRouteByTravelPlanId(@RequestBody TravelPlanIdDto travelPlanIdDto){
        return new Result<>(travelPlanDestinationService.getDestinationRouteByTravelPlanId(travelPlanIdDto));
    }

    @PostMapping
    public List<TravelPlanDestinationDto> addDestinationToTravelPlan(@RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return travelPlanDestinationService.addDestinationToTravelPlan(dto);
    }

    @PutMapping
    public List<TravelPlanDestinationDto> updateDestinationToTravelPlanById(@RequestBody DestinationRouteListWithTravelPlanIdDto dto){
        return travelPlanDestinationService.updateDestinationToTravelPlan(dto);
    }

}
