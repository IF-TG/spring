package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestUpdatePostScrapDto;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostScrapService {
    ToggleDto togglePostScrap(RequestScrapDto dto);

    List<ScrapDto> updateFolderName(RequestUpdatePostScrapDto dto);

    List<PostDto> findAllPostScrapsByScrapFolderAndUserId(String folderName, Long userId, Pageable pageable);
}
