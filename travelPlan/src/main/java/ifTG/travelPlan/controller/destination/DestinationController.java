package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
<<<<<<< HEAD
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
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
<<<<<<< HEAD
    public Result<List<ResponseEDestinationDto>> findAllByKeyword(@RequestBody RequestSearchDestinationDto requestSearchDestinationDto){
        return new Result<>(destinationService.findAllByKeyword(requestSearchDestinationDto));
=======
<<<<<<< HEAD
    public Result<List<ResponseEDestinationDto>> findAllByKeyword(@RequestBody RequestSearchDestinationDto dto){
=======
    public Result<List<EDestination>> findAllByKeyword(@RequestBody RequestSearchDestinationDto dto){
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
        return new Result<>(destinationService.findAllByKeyword(dto));
>>>>>>> bf6ed99a14e3d427dc70dda0a2faf472b70ae599
    }

    /*@GetMapping()
    public List<DestinationDto>*/
}
