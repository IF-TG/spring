package ifTG.travelPlan.service.destination;


import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;

public interface DestinationScrapService {

    ToggleDto toggleDestinationScrap(RequestScrapDto dto);

    ScrapDto updateFolderName(RequestScrapDto dto);
}
