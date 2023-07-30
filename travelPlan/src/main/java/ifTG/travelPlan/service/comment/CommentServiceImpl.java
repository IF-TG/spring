package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.controller.dto.NestedCommentIdDto;
import ifTG.travelPlan.controller.dto.NestedUpdateCommentDto;
import ifTG.travelPlan.controller.dto.RequestUpdateNestedCommentDto;
import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.post.comment.NestedComment;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.CommentUpdateDto;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.repository.springdata.NestedCommentRepository;
import ifTG.travelPlan.repository.springdata.post.CommentRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.post.PostViewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostViewService postViewService;
    private final PostRepository postRepository;
    private final NestedCommentRepository nestedCommentRepository;

    @Override
    public List<CommentDtoWithUserInfo> getCommentListByPost(RequestCommentByPostDto requestCommentByPostDto) {
        return getCommentList(requestCommentByPostDto);
    }
    @Override
    public List<CommentDtoWithUserInfo> getCommentListByPostAndSavePostView(RequestCommentByPostDto requestCommentByPostDto) {
        postViewService.addPostViewByPostIdAndUserId(requestCommentByPostDto.getPostId(), requestCommentByPostDto.getUserId());
        return getCommentList(requestCommentByPostDto);
    }
    @Override
    public CommentDtoWithUserInfo saveComment(RequestCreateCommentDto createCommentDto) {
        User user = getUser(createCommentDto.getUserId());
        Post post = getPost(createCommentDto.getPostId());
        Comment comment = Comment.builder()
                .comment(createCommentDto.getComment())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);
        return getCommentDto(user, comment, false);
    }

    @Override
    public Boolean deleteComment(CommentIdDto commentIdDto) {
        Comment comment = commentRepository.findById(commentIdDto.getCommentId()).orElseThrow(EntityNotFoundException::new);
        comment.deleteComment();
        commentRepository.save(comment);
        return true;
    }

    @Override
    public CommentUpdateDto updateComment(RequestUpdateCommentDto requestUpdateCommentDto) {
        Comment comment = commentRepository.findById(requestUpdateCommentDto.getCommentId()).orElseThrow(EntityNotFoundException::new);
        if(comment.isDeleted()){
            throw new RuntimeException("이미 삭제된 댓글입니다.");
        }
        comment.updateComment(requestUpdateCommentDto.getComment());
        return new CommentUpdateDto(comment.getId(), comment.getComment());
    }

    @Override
    public NestedCommentDto saveNestedComment(RequestCreateNestedCommentDto nestedCommentDto) {
        User user = getUser(nestedCommentDto.getUserId());
        Comment comment = getComment(nestedCommentDto.getCommentId());
        NestedComment nestedComment = NestedComment.builder()
                .parentComment(comment)
                .comment(nestedCommentDto.getComment())
                .user(user)
                .build();
        nestedCommentRepository.save(nestedComment);

        return getNestedCommentDto(nestedComment, false);
    }

    @Override
    public Boolean deleteNestedComment(NestedCommentIdDto nestedCommentIdDto) {
        nestedCommentRepository.deleteByNestedCommentId(nestedCommentIdDto.getNestedCommentId());
        return true;
    }

    @Override
    public NestedUpdateCommentDto updateNestedUpdateComment(RequestUpdateNestedCommentDto requestUpdateNestedCommentDto) {
        NestedComment nestedComment = nestedCommentRepository.findById(requestUpdateNestedCommentDto.getNestedCommentId()).orElseThrow(EntityNotFoundException::new);
        nestedComment.updateComment(requestUpdateNestedCommentDto.getComment());

        nestedComment = nestedCommentRepository.save(nestedComment);

        return new NestedUpdateCommentDto(nestedComment.getId(), nestedComment.getComment());
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);
    }

    private CommentDtoWithUserInfo getCommentDto(User user, Comment comment, boolean isLiked) {
        return CommentDtoWithUserInfo.builder()
                .commentId(comment.getId())
                .nickname(user.getNickname())
                .profileImgUri(user.getProfileImgUrl())
                .isLiked(isLiked)
                .createAt(comment.getCreatedAt())
                .likeNum(comment.getLikeNum())
                .comment(comment.getComment())
                .nestedCommentDtoList(null)
                .build();
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
    }


    public List<CommentDtoWithUserInfo> getCommentList(RequestCommentByPostDto requestCommentByPostDto){
        Long postId = requestCommentByPostDto.getPostId();
        Pageable pageable = requestCommentByPostDto.getPageable();
        User user = getUserWithCommentLikesAndNestedCommentLikes(requestCommentByPostDto.getUserId());
        Page<Comment> commentList = commentRepository.findAllWithNestedCommentWithUserByPostId(pageable, postId);

        List<Long> likedCommentIdListByUser = user.getCommentLikeList().stream().map(commentLike -> commentLike.getComment().getId()).toList();
        List<Long> likedNestedCommentIdListByUser = user.getNestedCommentLikeList().stream().map(nestedCommentLike -> nestedCommentLike.getNestedComment().getId()).toList();

        return getCommentDtoList(commentList, likedCommentIdListByUser, likedNestedCommentIdListByUser);
    }
    private User getUserWithCommentLikesAndNestedCommentLikes(Long userId) {
        return userRepository.findWithCommentLikeAndNestedCommentLikeByUserId(userId);
    }
    private List<CommentDtoWithUserInfo> getCommentDtoList(Page<Comment> commentList, List<Long> likedCommentIdList, List<Long> likedNestedCommentIdList) {
        return commentList.stream().map(comment ->
                getCommentDtoWithNestedDto(
                        comment,
                        likedNestedCommentIdList,
                        isLikedComment(likedCommentIdList, comment.getId())
                )).toList();
    }

    private CommentDtoWithUserInfo getCommentDtoWithNestedDto(Comment comment, List<Long> likedNestedCommentIdListByUser, boolean likedComment) {
        return CommentDtoWithUserInfo.builder()
                .commentId(comment.getId())
                .nickname(comment.getUser().getNickname())
                .isLiked(likedComment)
                .profileImgUri(comment.getUser().getProfileImgUrl())
                .createAt(comment.getCreatedAt())
                .likeNum(comment.getLikeNum())
                .comment(comment.getComment())
                .nestedCommentDtoList(
                        comment.getNestedCommentList().stream().map(
                                nestedComment -> getNestedCommentDto(nestedComment, isLikedComment(likedNestedCommentIdListByUser, nestedComment.getId()))
                        ).toList()
                ).build();
    }

    private NestedCommentDto getNestedCommentDto(NestedComment nestedComment, boolean isLiked) {
        return NestedCommentDto.builder()
                               .nestedCommentId(nestedComment.getId())
                               .nickname(nestedComment.getUser().getNickname())
                               .profileImgUri(nestedComment.getUser().getProfileImgUrl())
                               .createAt(nestedComment.getCreatedAt())
                               .comment(nestedComment.getComment())
                               .isLiked(isLiked)
                               .build();
    }

    private boolean isLikedComment(List<Long> likedCommentIdList, Long commentId) {
        return likedCommentIdList.contains(commentId);
    }
}
