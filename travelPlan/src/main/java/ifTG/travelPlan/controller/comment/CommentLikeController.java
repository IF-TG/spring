package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.dto.post.LikeDto;
import ifTG.travelPlan.service.comment.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("commentLike")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public LikeDto toggleLikeComment(@RequestBody RequestLikeDto requestLikeDto){
        return commentLikeService.toggleLikeComment(requestLikeDto);
    }


}
