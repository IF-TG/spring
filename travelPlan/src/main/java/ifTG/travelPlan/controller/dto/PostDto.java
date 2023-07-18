package ifTG.travelPlan.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import ifTG.travelPlan.domain.post.PostImg;
import ifTG.travelPlan.dto.post.enums.Companions;
import ifTG.travelPlan.dto.post.enums.Regions;
import ifTG.travelPlan.dto.post.enums.Seasons;
import ifTG.travelPlan.dto.post.enums.Themes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

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
        ,List<Themes> themes, List<Regions> regions, List<Seasons> seasons, List<Companions> companions, boolean isLiked) {
        this.postId = postId;
        this.profileImgUri = profileImgUri;
        this.title = title;
        this.nickname = nickname;
        this.startDate =  startDate.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        this.endDate =  endDate.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        this.postImgUri = postImgUri.stream()
                .map(PostImg::getImgURL)
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
        this.themes = themes.stream().map(Themes::getValue).toList();
        this.regions = regions.stream().map(Regions::getValue).toList();
        this.seasons = seasons.stream().map(Seasons::getValue).toList();
        this.companions = companions.stream().map(Companions::getValue).toList();
        this.isLiked = isLiked;
    }
}
