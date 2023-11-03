package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "post_view_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostView {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_view_history_id")
    private Long id;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @CreationTimestamp
    private LocalDateTime createAt;

    public PostView(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
