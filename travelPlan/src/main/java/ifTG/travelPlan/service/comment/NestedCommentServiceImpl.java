package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.NestedUpdateCommentDto;
import ifTG.travelPlan.controller.dto.RequestCreateNestedCommentDto;
import ifTG.travelPlan.controller.dto.RequestUpdateNestedCommentDto;
import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.converter.CommentDtoConverter;
import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.post.comment.NestedComment;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.comment.CommentRepository;
import ifTG.travelPlan.repository.springdata.comment.NestedCommentRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NestedCommentServiceImpl implements NestedCommentService{
    private final NestedCommentRepository nestedCommentRepository;
    private final CommentDtoConverter converter;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public NestedCommentDto saveNestedComment(Long userId, RequestCreateNestedCommentDto nestedCommentDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        Comment comment = commentRepository.findById(nestedCommentDto.getCommentId()).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_COMMENT));
        NestedComment nestedComment = NestedComment.builder()
                .parentComment(comment)
                .comment(nestedCommentDto.getComment())
                .user(user)
                .build();
        nestedCommentRepository.save(nestedComment);
        return converter.getNestedCommentDto(nestedComment, false);
    }

    @Override
    @Transactional
    public Boolean deleteNestedComment(Long userId, Long nestedCommentId) {
        NestedComment nestedComment = nestedCommentRepository.findById(nestedCommentId).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_COMMENT));
        Check.is(!nestedComment.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        nestedCommentRepository.delete(nestedComment);
        commentRepository.deleteCommentWithSoftDeletedNestedComments(nestedCommentId);
        return true;
    }

    @Override
    @Transactional
    public NestedUpdateCommentDto updateNestedUpdateComment(Long userId, RequestUpdateNestedCommentDto requestUpdateNestedCommentDto) {
        NestedComment nestedComment = nestedCommentRepository.findById(requestUpdateNestedCommentDto.getNestedCommentId()).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_COMMENT));
        Check.is(!nestedComment.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        nestedComment.updateComment(requestUpdateNestedCommentDto.getComment());

        nestedComment = nestedCommentRepository.save(nestedComment);

        return new NestedUpdateCommentDto(nestedComment.getId(), nestedComment.getComment());
    }

}
