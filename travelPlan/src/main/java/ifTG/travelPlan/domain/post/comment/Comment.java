package ifTG.travelPlan.domain.post.comment;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * id : comment의 primary키. 자동 생성되는 변수
 * comment : comment의 내용이 담기는 변수
 * createdAt : comment가 생성되는 날짜와 시간을 자동으로 입력받는다. 수정되는 시간도 필요하다면 @UpdateTimestamp 변수 추가
 * user : USERS 테이블과 user_id로 매핑하여 작성자의 정보를 저장
 * post : POSTS 테이블과 post_id로 매핑하여 작성된 post 정보 저장
 */
@Entity @Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Formula("(SELECT COUNT(1) FROM comment_likes c WHERE c.comment_id = comment_id)")
    private int likeNum;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "parentComment")
    private final List<NestedComment> nestedCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "comment", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private final List<CommentLike> commentLikeList = new ArrayList<>();

    @Builder
    public Comment(String comment, User user, Post post) {
        this.comment = comment;
        this.user = user;
        this.post = post;
    }

    public void deleteComment(){
        this.comment = "삭제된 댓글입니다.";
        this.isDeleted = true;
    }

    public void updateComment(String comment){
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return likeNum == comment1.likeNum && isDeleted == comment1.isDeleted && Objects.equals(id, comment1.id) && Objects.equals(comment, comment1.comment) && Objects.equals(createdAt, comment1.createdAt) && Objects.equals(user, comment1.user) && Objects.equals(post, comment1.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, createdAt, user, post, likeNum, isDeleted);
    }
}
