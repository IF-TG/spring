package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.NestedUpdateCommentDto;
import ifTG.travelPlan.controller.dto.RequestCreateNestedCommentDto;
import ifTG.travelPlan.controller.dto.RequestUpdateNestedCommentDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import org.springframework.transaction.annotation.Transactional;

public interface NestedCommentService {
    @Transactional
    NestedCommentDto saveNestedComment(Long userId, RequestCreateNestedCommentDto nestedCommentDto);

    @Transactional
    Boolean deleteNestedComment(Long userId, Long nestedCommentId);

    @Transactional
    NestedUpdateCommentDto updateNestedUpdateComment(Long userId, RequestUpdateNestedCommentDto requestUpdateNestedCommentDto);
}
