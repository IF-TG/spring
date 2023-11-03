package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestScrapDetail;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;

import java.util.List;

public interface PostScrapService {
    ToggleDto togglePostScrap(RequestScrapDto dto);

    ScrapDto updateFolderName(RequestScrapDto dto);

    List<PostDto> findAllPostScrapsByScrapFolderAndUserId(RequestScrapDetail dto);
}
