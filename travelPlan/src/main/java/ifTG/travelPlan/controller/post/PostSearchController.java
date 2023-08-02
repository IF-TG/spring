package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.service.post.PostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/postSearch")
public class PostSearchController {
    private final PostSearchService postSearchService;

    @GetMapping
    public List<PostDto> findAllLikeKeyword(RequestSearchPostDto requestSearchPostDto){
        return postSearchService.findAllLikeKeyword(requestSearchPostDto);
    }
}
