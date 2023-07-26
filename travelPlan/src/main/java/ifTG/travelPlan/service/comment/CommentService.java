package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.comment.NestedCommentIdDto;
import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.dto.comment.NestedUpdateCommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDtoWithUserInfo> getCommentListByPost(RequestCommentByPostDto requestCommentByPostDto);
    List<CommentDtoWithUserInfo> getCommentListByPostAndSavePostView(RequestCommentByPostDto requestCommentByPostDto);
    CommentDtoWithUserInfo saveComment(RequestCreateCommentDto createCommentDto);
    Boolean deleteComment(CommentIdDto commentIdDto);
    CommentUpdateDto updateComment(RequestUpdateCommentDto requestUpdateCommentDto);
    NestedCommentDto saveNestedComment(RequestCreateNestedCommentDto nestedCommentDto);
    Boolean deleteNestedComment(NestedCommentIdDto nestedCommentIdDto);
    NestedUpdateCommentDto updateNestedUpdateComment(RequestUpdateNestedCommentDto requestUpdateNestedCommentDto);
}
