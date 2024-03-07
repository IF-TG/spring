package ifTG.travelPlan.service.scrap;

import ifTG.travelPlan.service.destination.DestinationScrapService;
import ifTG.travelPlan.service.post.PostScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapDeleteServiceImpl implements ScrapDeleteService{
    private final DestinationScrapService destinationScrapService;
    private final PostScrapService postScrapService;

    @Override
    public Boolean deleteAllByFolderName(Long userId, String folderName){
        Boolean isDeletedDS = destinationScrapService.deleteAllByFolderName(userId, folderName);
        Boolean isDeletedPS = postScrapService.deleteAllByFolderName(userId, folderName);
        return isDeletedPS&&isDeletedDS;
    }

}
