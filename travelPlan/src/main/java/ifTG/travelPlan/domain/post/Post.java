package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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
@Slf4j
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

    @Column(columnDefinition = "DOUBLE DEFAULT 0")
    private Double score;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Formula("(SELECT COALESCE(COUNT(1),0) FROM post_likes l WHERE l.post_id = post_id)")
    private Integer likeNum;

    @Formula("(SELECT COALESCE(COUNT(1),0) FROM comments c WHERE c.post_id = post_id)")
    private Integer commentNum;

    private LocalDate startDate;
    private LocalDate endDate;


    //양방향 매핑
    @BatchSize(size = 500)
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private final List<PostImg> postImgList = new ArrayList<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private final List<PostLike> postLikeList = new ArrayList<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<PostCategory> postCategoryList = new HashSet<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private final List<PostView> postViewList = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PostScrap> postScrapList = new ArrayList<>();

    @Builder
    public Post(String title, String content, User user, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.user.getPostList().add(this); // 1차 캐시 문제
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Post(Long id, String title, String content, User user, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.user.getPostList().add(this); // 1차 캐시 문제
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * setter
     */

    public void updatePost(String title, String content, LocalDate startDate, LocalDate endDate){
        this.title = title;
        this.content = content;
        this.startDate =startDate;
        this.endDate = endDate;
    }

    public void addPostViewByUser(User user){
        postViewList.add(new PostView(new PostViewId(user.getId(), this.getId())));
    }

    public void addPostImgUri(PostImg postImg) {
        log.info("postImg = {}", postImg.getId());
        this.postImgList.add(postImg);
    }

    public void removePostImgById(String postImgFileName) {
        for(int i = 0; i<this.postImgList.size(); i++){
            if(postImgList.get(i).getFileName().equals(postImgFileName)){
                postImgList.remove(i);
                return;
            }
        }
    }
}