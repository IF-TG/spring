package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.dto.post.enums.Regions;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_regions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_region_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50)
    private Regions regions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostRegion(Regions regions, Post post) {
        this.regions = regions;
        this.post = post;
    }
}
