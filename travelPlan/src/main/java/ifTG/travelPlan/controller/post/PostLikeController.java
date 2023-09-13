package ifTG.travelPlan.controller.post;


import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;
import ifTG.travelPlan.service.post.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/postLike")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;
    @PostMapping
    public LikeDto toggleLikePost(@RequestBody RequestLikeDto requestLikeDto){
        log.info("likedto = {}, {}", requestLikeDto.getObjectId(), requestLikeDto.getUserId());
        return postLikeService.toggleLikePost(requestLikeDto);
    }
}
