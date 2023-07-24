package ifTG.travelPlan.controller.comment;

import ifTG.travelPlan.controller.dto.RequestCommentByPostDto;
import ifTG.travelPlan.controller.dto.RequestCreateCommentDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.comment.CommentDto;
import ifTG.travelPlan.service.comment.CommentService;
import ifTG.travelPlan.service.post.PostViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/byPost")
    public Result<List<CommentDto>> getCommentListByPostAndSavePostView(@RequestBody RequestCommentByPostDto requestCommentByPostDto){
        return new Result<>(commentService.getCommentListByPostAndSavePostView(requestCommentByPostDto));
    }

    @GetMapping("/comments")
    public Result<List<CommentDto>> getCommentList(@RequestBody RequestCommentByPostDto requestCommentByPostDto){
        return new Result<>(commentService.getCommentListByPost(requestCommentByPostDto));
    }

    @PostMapping
    public CommentDto saveComment(@RequestBody RequestCreateCommentDto createCommentDto){
        return commentService.saveComment(createCommentDto);
    }
    @DeleteMapping
    public Boolean deleteComment(@RequestBody CommentIdDto commentIdDto){
        return commentService.deleteComment(commentIdDto);
    }

}
