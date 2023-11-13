package ifTG.travelPlan.service.destination;


import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestUpdateDestinationScrapDto;
import ifTG.travelPlan.controller.dto.RequestScrapDetail;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DestinationScrapService {

    ToggleDto toggleDestinationScrap(RequestScrapDto dto);

    List<ScrapDto> updateDestinationScrap(RequestUpdateDestinationScrapDto dto);

    List<DestinationDto> findAllDestinationScrapsByScrapFolderAndUserId(String folderName, Long userId, Pageable pageable);

}
