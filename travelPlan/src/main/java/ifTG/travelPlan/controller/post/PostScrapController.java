package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.post.PostScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/postScrap")
@RequiredArgsConstructor
public class PostScrapController {
    private final PostScrapService postScrapService;

    @PostMapping
    public ToggleDto togglePostScrap(RequestScrapDto dto){
        return postScrapService.togglePostScrap(dto);
    }

    @PutMapping
    public ScrapDto updateFolderName(RequestScrapDto dto) {
        return postScrapService.updateFolderName(dto);
    }
}
