package ifTG.travelPlan.dto.comment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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

    @Builder
    public NestedCommentDto(Long nestedCommentId, String profileImgUri, String nickname, LocalDateTime createAt, String comment, boolean isLiked) {
        this.nestedCommentId = nestedCommentId;
        this.profileImgUri = profileImgUri;
        this.nickname = nickname;
        this.crateAt = createAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
        this.comment = comment;
        this.isLiked = isLiked;
    }
}
