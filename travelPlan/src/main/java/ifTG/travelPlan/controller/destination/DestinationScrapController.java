package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestUpdateDestinationScrapDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.service.destination.DestinationScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/destinationScrap")
public class DestinationScrapController {
    private final DestinationScrapService destinationScrapService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> toggleDestinationScrap(@RequestBody RequestScrapDto dto){
        return Result.isSuccess(destinationScrapService.toggleDestinationScrap(dto));
    }
    @PutMapping
    public ResponseEntity<Result<List<ScrapDto>>> updateDestinationScrap(@RequestBody RequestUpdateDestinationScrapDto dto) {
        return Result.isSuccess(destinationScrapService.updateDestinationScrap(dto));
    }

    @GetMapping("/detail")
    public ResponseEntity<Result<List<DestinationDto>>> findAllDestinationScrapsByScrapFolderAndUserId(
            @RequestParam String folderName,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        return Result.isSuccess(destinationScrapService.findAllDestinationScrapsByScrapFolderAndUserId(folderName, userId, PageRequest.of(page,perPage)));

    }
}
