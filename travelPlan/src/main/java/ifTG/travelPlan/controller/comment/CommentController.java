package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Result<CommentDtoWithUserInfo>> saveComment(@RequestBody RequestCreateCommentDto createCommentDto){
        return Result.isSuccess(commentService.saveComment(createCommentDto));
    }
    @GetMapping
    public ResponseEntity<Result<List<CommentDtoWithUserInfo>>> getCommentList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam Long postId,
            @RequestParam Long userId){
        return Result.isSuccess(commentService.getCommentListByPost(postId,userId,PageRequest.of(page,perPage)));

    }

    @DeleteMapping
    public ResponseEntity<Result<Boolean>> deleteComment(@RequestParam Long commentId){
        return Result.isSuccess(commentService.deleteComment(commentId));
    }

    @PutMapping
    public ResponseEntity<Result<CommentUpdateDto>> updateComment(@RequestBody RequestUpdateCommentDto requestUpdateCommentDto){
        return Result.isSuccess(commentService.updateComment(requestUpdateCommentDto));
    }

    @PostMapping("/nestedComment")
    public ResponseEntity<Result<NestedCommentDto>> saveNestedComment(@RequestBody RequestCreateNestedCommentDto nestedCommentDto){
        return Result.isSuccess(commentService.saveNestedComment(nestedCommentDto));
    }

    @DeleteMapping("/nestedComment")
    public ResponseEntity<Result<Boolean>> deleteNestedComment(@RequestParam Long nestedCommentId){
        return Result.isSuccess(commentService.deleteNestedComment(nestedCommentId));
    }
    @PutMapping("/nestedComment")
    public ResponseEntity<Result<NestedUpdateCommentDto>> updateNestedComment(@RequestBody RequestUpdateNestedCommentDto requestUpdateNestedCommentDto){
        return Result.isSuccess(commentService.updateNestedUpdateComment(requestUpdateNestedCommentDto));
    }   
}
