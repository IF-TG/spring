package ifTG.travelPlan.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.FetchType.*;
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

    @OneToMany(mappedBy = "parentComment")
    private List<NestedComment> nestedComment;
}
