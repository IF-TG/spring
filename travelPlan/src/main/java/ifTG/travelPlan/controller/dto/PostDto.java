package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.domain.post.PostImg;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    private final Long postId;
    private final String profileImgUri;
    private final String title;
    private final String nickname;
    private final String startDate;
    private final String endDate;
    private final List<PostImgDto> postImgUri;
    private final String content;
    private final Integer likeNum;
    private final Integer commentNum;
    private final String createAt;
    private final List<String> themes;
    private final List<String> regions;
    private final List<String> seasons;
    private final List<String> companions;
    private final boolean isLiked;
    private final Double mapX;
    private final Double mapY;

    @Builder
    public PostDto(Long postId, String profileImgUri, String title, String nickname, LocalDate startDate, LocalDate endDate, List<PostImgDto> postImgUri, String content, Integer likeNum, Integer commentNum, LocalDateTime createAt
        ,List<String> themes, List<String> regions, List<String> seasons, List<String> companions, boolean isLiked, Double mapX, Double mapY) {
        this.postId = postId;
        this.profileImgUri = profileImgUri;
        this.title = title;
        this.nickname = nickname;
        this.startDate =  startDate.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        this.endDate =  endDate.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        this.postImgUri = postImgUri;
        this.content = content;

        this.likeNum = Objects.requireNonNullElse(likeNum, 0);
        this.commentNum = Objects.requireNonNullElse(commentNum, 0);

        this.createAt = createAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.themes = themes;
        this.regions = regions;
        this.seasons = seasons;
        this.companions = companions;
        this.isLiked = isLiked;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
