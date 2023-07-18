package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.dto.post.enums.Themes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_themes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_theme_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Themes themes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostTheme(Themes themes, Post post) {
        this.themes = themes;
        this.post = post;
    }
}
