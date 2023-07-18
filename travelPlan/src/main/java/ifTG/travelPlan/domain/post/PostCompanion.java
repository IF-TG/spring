package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.dto.post.enums.Companions;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_companions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCompanion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_companion_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Companions companions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostCompanion(Companions companions, Post post) {
        this.companions = companions;
        this.post = post;
    }
}
