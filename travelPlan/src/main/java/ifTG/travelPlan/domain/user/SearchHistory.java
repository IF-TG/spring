package ifTG.travelPlan.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    private LocalDateTime search_time;

    @Column(nullable = false)
    private String history;

    public SearchHistory(User user, String history) {
        this.user = user;
        this.history = history;
    }

    public void updateTimeSearchHistory(){
        search_time = LocalDateTime.now();
    }
}
