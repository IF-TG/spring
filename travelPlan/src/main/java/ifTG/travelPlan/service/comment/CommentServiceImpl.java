package ifTG.travelPlan.service.comment;

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
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.post.PostViewService;
import ifTG.travelPlan.service.user.UserProfileImgService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostViewService postViewService;
    private final PostRepository postRepository;
    private final NestedCommentRepository nestedCommentRepository;
    private final UserBlockRepository userBlockRepository;
    private final UserProfileImgService userProfileImgService;

    @Override
    public List<CommentDtoWithUserInfo> getCommentListByPost(RequestCommentByPostDto requestCommentByPostDto) {
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
        Comment comment = commentRepository.findWithNestedCommentById(commentIdDto.getCommentId()).orElseThrow(EntityNotFoundException::new);
        if(comment.getNestedCommentList().isEmpty()){
            commentRepository.delete(comment);
        }else{
            comment.deleteComment();
            commentRepository.save(comment);
        }
        return true;
    }

    @Override
    public CommentUpdateDto updateComment(RequestUpdateCommentDto requestUpdateCommentDto) {
        Comment comment = commentRepository.findById(requestUpdateCommentDto.getCommentId()).orElseThrow(EntityNotFoundException::new);
        if(comment.isDeleted()){
            throw new RuntimeException("이미 삭제된 댓글입니다.");
        }
        comment.updateComment(requestUpdateCommentDto.getComment());
        commentRepository.save(comment);
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
        nestedCommentRepository.deleteById(nestedCommentIdDto.getNestedCommentId());
        commentRepository.deleteCommentWithSoftDeletedNestedComments(nestedCommentIdDto.getNestedCommentId());

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
                .profileImgUri(userProfileImgService.getProfileImgUrl(comment.getUser().getId(),comment.getUser().getProfileImgUrl()))
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


    public List<CommentDtoWithUserInfo> getCommentList(RequestCommentByPostDto dto){
        Long postId = dto.getPostId();
        User user = getUserWithCommentLikesAndNestedCommentLikes(dto.getUserId());
        Page<Comment> commentList = commentRepository.findAllWithNestedCommentWithUserByPostId(dto.getPageable(), postId);
        List<Long> blockedUserIdList = userBlockRepository.findBlockedUserIdListByUserId(dto.getUserId());

        List<Long> likedCommentIdListByUser = user.getCommentLikeList().stream().map(commentLike -> commentLike.getComment().getId()).toList();
        List<Long> likedNestedCommentIdListByUser = user.getNestedCommentLikeList().stream().map(nestedCommentLike -> nestedCommentLike.getNestedComment().getId()).toList();

        return getCommentDtoList(commentList, likedCommentIdListByUser, likedNestedCommentIdListByUser, blockedUserIdList);
    }
    private User getUserWithCommentLikesAndNestedCommentLikes(Long userId) {
        return userRepository.findWithCommentLikeAndNestedCommentLikeByUserId(userId);
    }
    private List<CommentDtoWithUserInfo> getCommentDtoList(Page<Comment> commentList, List<Long> likedCommentIdList,
                                                           List<Long> likedNestedCommentIdList, List<Long> blockedUserIdList) {
        return commentList.stream().map(comment ->
                getCommentDtoWithNestedDto(
                        comment,
                        likedNestedCommentIdList,
                        isLikedComment(likedCommentIdList, comment.getId()),
                        isBlockedComment(blockedUserIdList, comment.getUser().getId())
                )).toList();
    }



    private CommentDtoWithUserInfo getCommentDtoWithNestedDto(Comment comment, List<Long> likedNestedCommentIdListByUser, boolean likedComment, boolean blockedComment) {
        return CommentDtoWithUserInfo.builder()
                .commentId(comment.getId())
                .nickname(getNickname(comment, blockedComment))
                .isLiked(likedComment)
                .profileImgUri(blockedComment?null:userProfileImgService.getProfileImgUrl(comment.getUser().getId(),comment.getUser().getProfileImgUrl()))
                .createAt(comment.getCreatedAt())
                .likeNum(comment.getLikeNum())
                .comment(blockedComment?null:comment.getComment())
                .isDeleted(comment.isDeleted())
                .isBlocked(blockedComment)
                .nestedCommentDtoList(getNestedCommentDtoList(comment, likedNestedCommentIdListByUser)).build();
    }

    private List<NestedCommentDto> getNestedCommentDtoList(Comment comment, List<Long> likedNestedCommentIdListByUser) {
        List<NestedComment> nestedCommentList = comment.getNestedCommentList();
        nestedCommentList.sort(Comparator.comparing(NestedComment::getCreatedAt));
        return nestedCommentList.stream().map(
                nestedComment -> getNestedCommentDto(nestedComment, isLikedComment(likedNestedCommentIdListByUser, nestedComment.getId()))
        ).toList();
    }

    private static String getNickname(Comment comment, boolean blockedComment) {
        String nickname = null;
        if(!comment.isDeleted()&&!blockedComment){
            nickname = comment.getUser().getNickname();
        }
        return nickname;
    }

    private NestedCommentDto getNestedCommentDto(NestedComment nestedComment, boolean isLiked) {
        return NestedCommentDto.builder()
                               .nestedCommentId(nestedComment.getId())
                               .nickname(nestedComment.getUser().getNickname())
                               .profileImgUri(userProfileImgService.getProfileImgUrl(nestedComment.getUser().getId(),nestedComment.getUser().getProfileImgUrl()))
                               .createAt(nestedComment.getCreatedAt())
                               .comment(nestedComment.getComment())
                               .isLiked(isLiked)
                                .likeNum(nestedComment.getLikeNum())
                               .build();
    }

    private boolean isLikedComment(List<Long> likedCommentIdList, Long commentId) {
        return likedCommentIdList.contains(commentId);
    }
    private boolean isBlockedComment(List<Long> blockedUserIdList, Long userId) {
        return blockedUserIdList.contains(userId);
    }
}
