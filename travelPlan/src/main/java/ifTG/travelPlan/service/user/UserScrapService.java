package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestCreateScrapFolderDto;
import ifTG.travelPlan.controller.dto.RequestGetAllScrapByUserDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.user.ScrapFolderDto;
import ifTG.travelPlan.dto.user.ScrapTitleDto;

import java.util.List;

public interface UserScrapService {
    ScrapTitleDto createScrapFolder(RequestCreateScrapFolderDto requestCreateScrapFolderDto);

    List<ScrapFolderDto> findAllScrapByUser(RequestGetAllScrapByUserDto dto);
}
