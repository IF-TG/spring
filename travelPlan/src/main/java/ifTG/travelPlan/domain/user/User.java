package ifTG.travelPlan.domain.user;

import ifTG.travelPlan.domain.diary.Diary;
import ifTG.travelPlan.domain.post.Comment;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.travel.TravelPlan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

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
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_address_id", nullable = false)
    private UserAddress userAddress;

    //양방향 매핑
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;
    @OneToMany(mappedBy = "user")
    private List<Post> postList;
    @OneToMany(mappedBy = "user")
    private List<TravelPlan> travelPlanList;
    @OneToMany(mappedBy = "user")
    private List<Diary> diaryList;
    @OneToMany(mappedBy = "user")
    private List<ScrapFolder> scrapFolderList;
    @OneToMany(mappedBy = "user")
    private List<SearchHistory> searchHistorieList;
    @Builder
    public User(String userId, String pw, String name, Sex sex, LocalDate birthDate, String phoneNumber, String email, UserAddress userAddress) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAddress = userAddress;
        this.userAddress.addUser(this);
    }


    /**
     * 1차 캐싱 npe 방지 매핑용
     */

}
