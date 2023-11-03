package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestUpdateDestinationScrapDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.RequestScrapDetail;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.service.destination.DestinationScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/destinationScrap")
public class DestinationScrapController {
    private final DestinationScrapService destinationScrapService;

    @PostMapping
    public ToggleDto toggleDestinationScrap(@RequestBody RequestScrapDto dto){
        return destinationScrapService.toggleDestinationScrap(dto);
    }
    @PutMapping
    public Result<List<ScrapDto>> updateDestinationScrap(@RequestBody RequestUpdateDestinationScrapDto dto) {
        return new Result<>(destinationScrapService.updateDestinationScrap(dto));
    }

    @GetMapping("/detail")
    public Result<List<DestinationDto>> findAllDestinationScrapsByScrapFolderAndUserId(@RequestBody RequestScrapDetail dto){
        return new Result<>(destinationScrapService.findAllDestinationScrapsByScrapFolderAndUserId(dto));
    }
}
