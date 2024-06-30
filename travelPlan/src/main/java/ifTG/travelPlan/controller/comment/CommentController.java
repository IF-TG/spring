package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.RequestCreateCommentDto;
import ifTG.travelPlan.controller.dto.RequestUpdateCommentDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.service.comment.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Result<List<CommentDtoWithUserInfo>>> getCommentList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam Long postId,
            @AuthenticationUser Optional<Long> userId){
        return userId
                .map(aLong -> Result.isSuccess(commentService.getCommentListByPost(postId, aLong, PageRequest.of(page, perPage))))
                .orElseGet(() -> Result.isSuccess(commentService.getCommentListByPost(postId, PageRequest.of(page, perPage))));
    }

    @PostMapping
    public ResponseEntity<Result<CommentDtoWithUserInfo>> saveComment(@AuthenticationUser Long userId, @RequestBody RequestCreateCommentDto createCommentDto){
        return Result.isSuccess(commentService.saveComment(userId, createCommentDto));
    }

    @DeleteMapping
    public ResponseEntity<Result<Boolean>> deleteComment(@AuthenticationUser Long userId, @RequestParam Long commentId){
        return Result.isSuccess(commentService.deleteComment(userId, commentId));
    }

    @PutMapping
    public ResponseEntity<Result<CommentUpdateDto>> updateComment(@AuthenticationUser Long userId, @RequestBody RequestUpdateCommentDto requestUpdateCommentDto){
        return Result.isSuccess(commentService.updateComment(userId, requestUpdateCommentDto));
    }
}
