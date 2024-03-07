package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.NestedUpdateCommentDto;
import ifTG.travelPlan.controller.dto.RequestCreateNestedCommentDto;
import ifTG.travelPlan.controller.dto.RequestUpdateNestedCommentDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.service.comment.NestedCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment/nestedComment")
@RequiredArgsConstructor
public class NestedCommentController {
    private final NestedCommentService nestedCommentService;
    @PostMapping()
    public ResponseEntity<Result<NestedCommentDto>> saveNestedComment(@AuthenticationUser Long userId, @RequestBody RequestCreateNestedCommentDto nestedCommentDto){
        return Result.isSuccess(nestedCommentService.saveNestedComment(userId, nestedCommentDto));
    }

    @DeleteMapping()
    public ResponseEntity<Result<Boolean>> deleteNestedComment(@AuthenticationUser Long userId, @RequestParam Long nestedCommentId){
        return Result.isSuccess(nestedCommentService.deleteNestedComment(userId,nestedCommentId));
    }
    @PutMapping()
    public ResponseEntity<Result<NestedUpdateCommentDto>> updateNestedComment(@AuthenticationUser Long userId, @RequestBody RequestUpdateNestedCommentDto requestUpdateNestedCommentDto){
        return Result.isSuccess(nestedCommentService.updateNestedUpdateComment(userId, requestUpdateNestedCommentDto));
    }
}
