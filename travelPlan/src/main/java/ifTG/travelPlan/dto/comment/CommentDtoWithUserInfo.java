package ifTG.travelPlan.dto.comment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class CommentDtoWithUserInfo {
    private final Long commentId;
    private final String profileImgUri;
    private final String nickname;
    private final boolean isLiked;
    private final String createAt;
    private final int LikeNum;
    private final String comment;
    private List<NestedCommentDto> nestedCommentDtoList;
    private final boolean isDeleted;

    @Builder
    public CommentDtoWithUserInfo(Long commentId, String profileImgUri, String nickname, boolean isLiked, LocalDateTime createAt, int likeNum, String comment, List<NestedCommentDto> nestedCommentDtoList, boolean isDeleted) {
        this.commentId = commentId;
        this.profileImgUri = profileImgUri;
        this.nickname = nickname;
        this.isLiked = isLiked;
        this.createAt = createAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
        this.LikeNum = likeNum;
        this.comment = comment;
        this.nestedCommentDtoList = nestedCommentDtoList;
        this.isDeleted = isDeleted;
    }


    public void setNestedComment(List<NestedCommentDto> nestedCommentList){
        this.nestedCommentDtoList = nestedCommentList;
    }
}
