package ifTG.travelPlan.controller.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.controller.dto.RequestSaveDestinationToTravelPlanDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.dto.travel.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;
import ifTG.travelPlan.service.destinationroute.DestinationRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/destinationRoute")
@RequiredArgsConstructor
public class DestinationRouteController {
    private final DestinationRouteService destinationRouteService;


    @GetMapping
    public Result<List<DestinationRouteDto>> getDestinationRouteByTravelPlanId(TravelPlanIdDto travelPlanIdDto){
        return new Result<>(destinationRouteService.getDestinationRouteByTravelPlanId(travelPlanIdDto));
    }

    @PostMapping
    public List<DestinationRouteDto> addDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto){
        return destinationRouteService.addDestinationToTravelPlan(dto);
    }

    @PutMapping
    public List<DestinationRouteDto> updateDestinationToTravelPlanById(DestinationRouteListWithTravelPlanIdDto dto){
        return destinationRouteService.updateDestinationToTravelPlan(dto);
    }
}
