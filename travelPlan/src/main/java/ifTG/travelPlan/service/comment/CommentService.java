package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    List<CommentDtoWithUserInfo> getCommentListByPost(Long postId, Long userId, Pageable pageable);
    CommentDtoWithUserInfo saveComment(RequestCreateCommentDto createCommentDto);
    Boolean deleteComment(Long commentId);
    CommentUpdateDto updateComment(RequestUpdateCommentDto requestUpdateCommentDto);
    NestedCommentDto saveNestedComment(RequestCreateNestedCommentDto nestedCommentDto);
    Boolean deleteNestedComment(Long nestedCommentId);
    NestedUpdateCommentDto updateNestedUpdateComment(RequestUpdateNestedCommentDto requestUpdateNestedCommentDto);
}
