package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.comment.CommentIdDto;
import ifTG.travelPlan.controller.dto.RequestCommentByPostDto;
import ifTG.travelPlan.controller.dto.RequestCreateCommentDto;
import ifTG.travelPlan.dto.comment.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentListByPost(RequestCommentByPostDto requestCommentByPostDto);
    List<CommentDto> getCommentListByPostAndSavePostView(RequestCommentByPostDto requestCommentByPostDto);
    CommentDto saveComment(RequestCreateCommentDto createCommentDto);

    Boolean deleteComment(CommentIdDto commentIdDto);
}
