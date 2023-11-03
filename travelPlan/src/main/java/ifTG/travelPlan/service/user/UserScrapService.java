package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestRenameScrapFolder;
import ifTG.travelPlan.controller.dto.RequestScrapFolderDto;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;


import java.util.List;

public interface UserScrapService {
    List<UserScrapFolderDto> findAllScrapFolderByUser(RequestScrapFolderDto dto);
    List<UserScrapFolderDto> renameScrapFolder(RequestRenameScrapFolder dto);

}
