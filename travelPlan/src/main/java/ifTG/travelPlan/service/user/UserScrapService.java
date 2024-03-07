package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestRenameScrapFolder;
import ifTG.travelPlan.controller.dto.RequestScrapFolderDto;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;


import java.util.List;

public interface UserScrapService {
    List<UserScrapFolderDto> findAllScrapFolderByUser(Long userId);
    List<UserScrapFolderDto> renameScrapFolder(Long userId, RequestRenameScrapFolder dto);

}
