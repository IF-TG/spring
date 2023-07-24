package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/byPost")
    public Result<List<CommentDtoWithUserInfo>> getCommentListByPostAndSavePostView(@RequestBody RequestCommentByPostDto requestCommentByPostDto){
        return new Result<>(commentService.getCommentListByPostAndSavePostView(requestCommentByPostDto));
    }

    @GetMapping("/comments")
    public Result<List<CommentDtoWithUserInfo>> getCommentList(@RequestBody RequestCommentByPostDto requestCommentByPostDto){
        return new Result<>(commentService.getCommentListByPost(requestCommentByPostDto));
    }

    @PostMapping
    public CommentDtoWithUserInfo saveComment(@RequestBody RequestCreateCommentDto createCommentDto){
        return commentService.saveComment(createCommentDto);
    }

    @DeleteMapping
    public Boolean deleteComment(@RequestBody CommentIdDto commentIdDto){
        return commentService.deleteComment(commentIdDto);
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
    public Boolean deleteNestedComment(@RequestBody NestedCommentIdDto nestedCommentIdDto){
        return commentService.deleteNestedComment(nestedCommentIdDto);
    }
    @PutMapping("/nestedComment")
    public NestedUpdateCommentDto updateNestedComment(@RequestBody RequestBodtUpdateNestedCommentDto requestBodtUpdateNestedCommentDto){
        return commentService.updateNestedUpdateComment(requestBodtUpdateNestedCommentDto);
    }   
}
