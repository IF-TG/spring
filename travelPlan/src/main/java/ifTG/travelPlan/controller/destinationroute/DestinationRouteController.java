package ifTG.travelPlan.controller.destinationroute;

import ifTG.travelPlan.dto.travel.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;
import ifTG.travelPlan.service.destinationroute.DestinationRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/destinationRoute")
@RequiredArgsConstructor
public class DestinationRouteController {
    private final DestinationRouteService destinationRouteService;


    @GetMapping
    public Result<List<DestinationRouteDto>> e(TravelPlanIdDto travelPlanIdDto){
        return new Result<>(destinationRouteService.getDestinationRouteByTravelPlanId(travelPlanIdDto));
    }
}
