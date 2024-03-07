package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    List<CommentDtoWithUserInfo> getCommentListByPost(Long postId, Long userId, Pageable pageable);
    List<CommentDtoWithUserInfo> getCommentListByPost(Long postId, Pageable pageable);
    CommentDtoWithUserInfo saveComment(Long userId,RequestCreateCommentDto createCommentDto);
    Boolean deleteComment(Long userId, Long commentId);
    CommentUpdateDto updateComment(Long userId, RequestUpdateCommentDto requestUpdateCommentDto);

}
