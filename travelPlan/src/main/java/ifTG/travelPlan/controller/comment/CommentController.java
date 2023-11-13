package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDtoWithUserInfo saveComment(@RequestBody RequestCreateCommentDto createCommentDto){
        return commentService.saveComment(createCommentDto);
    }
    @GetMapping
    public Result<List<CommentDtoWithUserInfo>> getCommentList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam Long postId,
            @RequestParam Long userId){
        return new Result<>(commentService.getCommentListByPost(postId, userId, PageRequest.of(page,perPage)));
    }

    @DeleteMapping
    public Boolean deleteComment(@RequestParam Long commentId){
        return commentService.deleteComment(commentId);
    }

    @PutMapping
    public CommentUpdateDto updateComment(@RequestBody RequestUpdateCommentDto requestUpdateCommentDto){
        return commentService.updateComment(requestUpdateCommentDto);
    }

    @PostMapping("/nestedComment")
    public NestedCommentDto saveNestedComment(@RequestBody RequestCreateNestedCommentDto nestedCommentDto){
        return commentService.saveNestedComment(nestedCommentDto);
    }

    @DeleteMapping("/nestedComment")
    public Boolean deleteNestedComment(@RequestParam Long nestedCommentId){
        return commentService.deleteNestedComment(nestedCommentId);
    }
    @PutMapping("/nestedComment")
    public NestedUpdateCommentDto updateNestedComment(@RequestBody RequestUpdateNestedCommentDto requestUpdateNestedCommentDto){
        return commentService.updateNestedUpdateComment(requestUpdateNestedCommentDto);
    }   
}
