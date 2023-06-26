package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

/**
 * id : post의 primary키이며 자동생성
 * title : post의 title
 * content : post의 실제 내용이 담기는 변수
 * createAt : post의 생성 시간을 자동으로 담기는 변수
 * likes : 사용자들의 좋아요 개수를 나타내는 변수, default는 0
 * user : USERS 테이블과 user_id로 매핑
 * postImgList : POST_IMGS 테이블과 양방향 매핑이 되어있다. CascadeType.MERGE와
 *      orphanRemoval = TRUE 이므로 연결이 끊기거나 POST가 삭제될 시 연결된 POST_IMG도 삭제
 * commentList : COMMENTS 테이블과 양방향 매핑이 되어있다. 마찬가지로 POST가 삭제되거나
 *      변경 시 자식에게도 영향, 참조가 끊기면 해당 COMMENTS는 삭제된다.
 */
@Entity @Getter
@Table(name = "posts")
@NoArgsConstructor(access = PROTECTED)
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt;

    @Formula("(SELECT COUNT(1) FROM post_likes l WHERE l.post_id = post_id)")
    private Integer likeNum;

    @Formula("(SELECT COUNT(1) FROM comments c WHERE c.post_id = post_id)")
    private Integer commentNum;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    //양방향 매핑
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<PostImg> postImgList;

    //@BatchSize(size = 1000)
    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post", cascade = ALL)
    private List<PostLike> postLikes;

    @Builder
    public Post(Long id, String title, String content, LocalDateTime createAt, User user, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.user = user;
        this.user.getPostList().add(this);
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
