package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.controller.dto.RequestCommentByPostDto;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;
import ifTG.travelPlan.service.post.PostDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/postDetail")
@RequiredArgsConstructor
public class PostDetailController {
    private final PostDetailService postDetailService;
    @GetMapping
    public PostDetailsWithIsScraped getPostDetail(@RequestBody RequestCommentByPostDto dto){
        return postDetailService.getPostDetail(dto);
    }
}
