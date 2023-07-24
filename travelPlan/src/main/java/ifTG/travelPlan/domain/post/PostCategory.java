package ifTG.travelPlan.domain.post;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "post_categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50)
    private MainCategory mainCategory;

    @Column(length = 50)
    private String subCategory;

    public PostCategory(Post post, MainCategory mainCategory, Enum<?> subCategory) {
        this.post = post;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory.toString();
    }
}
