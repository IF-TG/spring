package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.RequestCreateCommentDto;
import ifTG.travelPlan.controller.dto.RequestUpdateCommentDto;
import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.converter.CommentDtoConverter;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.comment.CommentLikeRepository;
import ifTG.travelPlan.repository.springdata.comment.CommentRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserBlockRepository userBlockRepository;
    private final CommentDtoConverter converter;
    private final PostRepository postRepository;
    private final CommentLikeRepository commentLikeRepository;
    @Override
    public List<CommentDtoWithUserInfo> getCommentListByPost(Long postId, Long userId, Pageable pageable) {
        User user =  userRepository.findWithCommentLikeAndNestedCommentLikeByUserId(userId);
        Page<Comment> commentList = commentRepository.findAllWithUserByPostId(pageable, postId);
        List<Long> blockedUserIdList = userBlockRepository.findBlockedUserIdListByUserId(userId);

        List<Long> likedCommentIdListByUser = user.getCommentLikeList().stream().map(commentLike -> commentLike.getComment().getId()).toList();
        List<Long> likedNestedCommentIdListByUser = user.getNestedCommentLikeList().stream().map(nestedCommentLike -> nestedCommentLike.getNestedComment().getId()).toList();

        return converter.getCommentDtoList(commentList, likedCommentIdListByUser, likedNestedCommentIdListByUser, blockedUserIdList);
    }

    @Override
    public List<CommentDtoWithUserInfo> getCommentListByPost(Long postId, Pageable pageable) {
        Page<Comment> commentList = commentRepository.findAllWithUserByPostId(pageable, postId);
        return converter.getCommentDtoList(commentList, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Override
    @Transactional
    public CommentDtoWithUserInfo saveComment(Long userId, RequestCreateCommentDto createCommentDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        Post post = postRepository.findById(createCommentDto.getPostId()).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_POST));
        Comment comment = Comment.builder()
                .comment(createCommentDto.getComment())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);
        return converter.getCommentDto(user, comment, false);
    }

    @Override
    @Transactional
    public Boolean deleteComment(Long userId, Long commentId){
        Comment comment = commentRepository.findWithNestedCommentById(commentId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_COMMENT));
        Check.is(!comment.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        if(comment.getNestedCommentList().isEmpty()){
            commentRepository.delete(comment);
        }else{
            commentLikeRepository.deleteByCommentId(comment.getId());
            comment.deleteComment();
            commentRepository.save(comment);
        }
        return true;
    }

    @Override
    @Transactional
    public CommentUpdateDto updateComment(Long userId, RequestUpdateCommentDto requestUpdateCommentDto) {
        Comment comment = commentRepository.findById(requestUpdateCommentDto.getCommentId()).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_COMMENT));
        Check.is(comment.isDeleted(), StatusCode.IS_ALREADY_DELETE);
        Check.is(!comment.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        comment.updateComment(requestUpdateCommentDto.getComment());
        commentRepository.save(comment);
        return new CommentUpdateDto(comment.getId(), comment.getComment());
    }


}
