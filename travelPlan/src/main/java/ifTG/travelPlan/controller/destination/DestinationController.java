package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.service.destination.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/destination")
@RequiredArgsConstructor
@Slf4j
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping("/search")
    public Result<List<ResponseEDestinationDto>> findAllByKeyword(@RequestBody RequestSearchDestinationDto requestSearchDestinationDto){
        return new Result<>(destinationService.findAllByKeyword(requestSearchDestinationDto));
    }

    /*@GetMapping()
    public List<DestinationDto>*/
}
