package ifTG.travelPlan.domain.user;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.post.comment.CommentLike;
import ifTG.travelPlan.domain.post.comment.NestedCommentLike;
import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.travel.TravelPlan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class User {
    @Id
    @Column(name = "id")
    private Long id;
    private String profileImgUrl;
    @Column(length = 30, nullable = true)
    private String nickname;
    @CreationTimestamp
    private LocalDateTime createAt;

    //양방향 매핑
    @OneToMany(mappedBy = "user")
    private final List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<Post> postList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<TravelPlan> travelPlanList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<PostScrap> postScrapList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<DestinationScrap> destinationScrapList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<SearchHistory> searchHistoryList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<UserLog> userLogList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<UserBlock> userBlockList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final List<PostLike> postLikeList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private final Set<CommentLike> commentLikeList = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private final Set<NestedCommentLike> nestedCommentLikeList = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private final List<UserVector> userVectorList = new ArrayList<>();
    public User(Long userId) {
        this.id = userId;
    }

    /**
     * setter
     */

    public void updateProfileImgUrl(String url){
        this.profileImgUrl = url;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }
}
