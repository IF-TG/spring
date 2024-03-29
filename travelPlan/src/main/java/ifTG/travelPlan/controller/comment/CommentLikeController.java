package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.comment.CommentLikeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment/like")
@SecurityRequirement(name = "Authorization")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> toggleLikeComment(@AuthenticationUser Long userId, @RequestBody RequestLikeDto requestLikeDto){
        return Result.isSuccess(commentLikeService.toggleLikeComment(userId, requestLikeDto));
    }


}
