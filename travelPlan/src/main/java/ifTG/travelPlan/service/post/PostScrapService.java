package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;

public interface PostScrapService {
    ToggleDto togglePostScrap(RequestScrapDto dto);

    ScrapDto updateFolderName(RequestScrapDto dto);
}
