package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.dto.PostScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.destination.DestinationScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/destinationScrap")
public class DestinationScrapController {
    private final DestinationScrapService destinationScrapService;

    public ToggleDto toggleDestinationScrap(RequestScrapDto dto){
        return destinationScrapService.toggleDestinationScrap(dto);
    }
    @PutMapping
    public PostScrapDto updateFolderName(RequestScrapDto dto) {
        return destinationScrapService.updateFolderName(dto);
    }
}
