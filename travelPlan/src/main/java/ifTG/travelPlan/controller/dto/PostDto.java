package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.domain.post.PostImg;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    private final Long postId;
    private final String profileImgUri;
    private final String title;
    private final String nickname;
    private final String startDate;
    private final String endDate;
    private final List<String> postImgUri;
    private final String content;
    private final Integer likeNum;
    private final Integer commentNum;
    private final String createAt;
    private final List<String> themes;
    private final List<String> regions;
    private final List<String> seasons;
    private final List<String> companions;
    private final boolean isLiked;
    @Builder
    public PostDto(Long postId, String profileImgUri, String title, String nickname, LocalDate startDate, LocalDate endDate, List<PostImg> postImgUri, String content, Integer likeNum, Integer commentNum, LocalDateTime createAt
        ,List<String> themes, List<String> regions, List<String> seasons, List<String> companions, boolean isLiked) {
        this.postId = postId;
        this.profileImgUri = profileImgUri;
        this.title = title;
        this.nickname = nickname;
        this.startDate =  startDate.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        this.endDate =  endDate.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        this.postImgUri = postImgUri.stream()
                .map(PostImg::getFileName)
                .collect(Collectors.toList());
        this.content = content;

        if (likeNum==null){
            this.likeNum = 0;
        }else{
            this.likeNum = likeNum;
        }
        if (commentNum==null){
            this.commentNum = 0;
        }else{
            this.commentNum = commentNum;
        }

        this.createAt = createAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
        this.themes = themes;
        this.regions = regions;
        this.seasons = seasons;
        this.companions = companions;
        this.isLiked = isLiked;
    }
}
