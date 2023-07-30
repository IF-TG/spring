package ifTG.travelPlan.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_history_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(nullable = false, name ="userId", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String history;
}
