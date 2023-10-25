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
public class User {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 20, nullable = false, unique = true)
    private String userId;
    @Column(length = 64, nullable = false)
    private String pw;
    @Column(length = 20, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(length = 20, nullable = false)
    private String phoneNumber;
    private String profileImgUrl;
    @Column(nullable = false)
    private String email;
    @Column(length = 30, nullable = true)
    private String nickname;

    @CreationTimestamp
    private LocalDateTime createAt;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_address_id", nullable = false)
    private UserAddress userAddress;

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
    private final Set<UserBlock> userBlockList = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private final Set<PostLike> postLikeList = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private final Set<CommentLike> commentLikeList = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private final Set<NestedCommentLike> nestedCommentLikeList = new HashSet<>();

    @Builder
    public User(String userId, String pw, String nickname, String name, Sex sex, LocalDate birthDate, String phoneNumber, String email, UserAddress userAddress) {
        this.userId = userId;
        this.pw = pw;
        this.nickname = nickname;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAddress = userAddress;
        this.userAddress.addUser(this); //1차 캐시 문제 해결
    }

}
