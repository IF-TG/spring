package ifTG.travelPlan.dto.comment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class NestedCommentDto {
    private final Long nestedCommentId;
    private final String profileImgUri;
    private final String nickname;
    private final String crateAt;
    private final String comment;
    private final boolean isLiked;
    private final int likeNum;

    @Builder
    public NestedCommentDto(Long nestedCommentId, String profileImgUri, String nickname, LocalDateTime createAt, String comment, boolean isLiked, int likeNum) {
        this.nestedCommentId = nestedCommentId;
        this.profileImgUri = profileImgUri;
        this.nickname = nickname;
        this.crateAt = createAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.comment = comment;
        this.isLiked = isLiked;
        this.likeNum = likeNum;
    }
}
