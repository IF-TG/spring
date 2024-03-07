package ifTG.travelPlan.converter;

import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.post.comment.NestedComment;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.service.user.UserProfileImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDtoConverterImpl implements CommentDtoConverter {
    private final UserProfileImgService userProfileImgService;

    @Override
    public CommentDtoWithUserInfo getCommentDto(User user, Comment comment, boolean isLiked) {
        return CommentDtoWithUserInfo.builder()
                .commentId(comment.getId())
                .nickname(user.getNickname())
                .profileImgUri(getProfileImgUrl(comment))
                .isLiked(isLiked)
                .createAt(comment.getCreatedAt())
                .likeNum(comment.getLikeNum())
                .comment(comment.getComment())
                .nestedCommentDtoList(null)
                .build();
    }



    @Override
    public List<CommentDtoWithUserInfo> getCommentDtoList(Page<Comment> commentList, List<Long> likedCommentIdList,
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
                .profileImgUri(blockedComment?null: getProfileImgUrl(comment))
                .createAt(comment.getCreatedAt())
                .likeNum(comment.getLikeNum())
                .comment(blockedComment?null:comment.getComment())
                .isDeleted(comment.isDeleted())
                .isBlocked(blockedComment)
                .nestedCommentDtoList(getNestedCommentDtoList(comment, likedNestedCommentIdListByUser)).build();
    }


    @Override
    public List<NestedCommentDto> getNestedCommentDtoList(Comment comment, List<Long> likedNestedCommentIdListByUser) {
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

    @Override
    public NestedCommentDto getNestedCommentDto(NestedComment nestedComment, boolean isLiked) {
        return NestedCommentDto.builder()
                .nestedCommentId(nestedComment.getId())
                .nickname(nestedComment.getUser().getNickname())
                .profileImgUri(getProfileImgUrl(nestedComment))
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

    private String getProfileImgUrl(Comment comment) {
        if (comment.getUser().getProfileImgUrl()==null)return null;
        return userProfileImgService.getProfileImgUrl(comment.getUser().getId(), comment.getUser().getProfileImgUrl());
    }

    private String getProfileImgUrl(NestedComment nestedComment) {
        if (nestedComment.getUser().getProfileImgUrl()==null)return null;
        return userProfileImgService.getProfileImgUrl(nestedComment.getUser().getId(), nestedComment.getUser().getProfileImgUrl());
    }
}
