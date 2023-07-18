package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.dto.post.enums.Seasons;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_seasons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_season_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Seasons seasons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostSeason(Seasons seasons, Post post) {
        this.seasons = seasons;
        this.post = post;
    }
}
