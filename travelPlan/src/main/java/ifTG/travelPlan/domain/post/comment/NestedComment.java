package ifTG.travelPlan.domain.post.comment;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "nested_comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NestedComment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Formula("(SELECT COUNT(1) FROM nested_comment_like c WHERE c.nested_comment_id = comment_id)")
    private int likeNum;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "nestedComment", orphanRemoval = true)
    private final List<NestedCommentLike> nestedCommentLikeList = new ArrayList<>();
    @Builder
    public NestedComment(String comment, User user, Comment parentComment) {
        this.comment = comment;
        this.user = user;
        this.parentComment = parentComment;
    }

    /**
     * setter
     */
    public void updateComment(String comment){
        this.comment = comment;
    }
}
