package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestScrapFolderDto;
import ifTG.travelPlan.controller.user.RequestScrapDetail;
import ifTG.travelPlan.dto.ScrapPostAndDestination;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;


import java.util.List;

public interface UserScrapService {
    List<UserScrapFolderDto> findAllScrapFolderByUser(RequestScrapFolderDto dto);

    List<ScrapPostAndDestination> findAllScrapsByScrapFolderAndUserId(RequestScrapDetail dto);
}
