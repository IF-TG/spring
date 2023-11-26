package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.comment.NestedCommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nestedCommentLike")
public class NestedCommentLikeController {
    private final NestedCommentLikeService nestedCommentLikeService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> toggleLikeNestedComment(@RequestBody RequestLikeDto requestLikeDto){
        return Result.isSuccess(nestedCommentLikeService.toggleLikeNestedComment(requestLikeDto));
    }
}
