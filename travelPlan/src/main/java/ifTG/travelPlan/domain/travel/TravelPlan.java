package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;

import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity @Getter
@Table(name = "travel_plans")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlan {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "travel_plan_id")
    private Long travelPlanId;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //양방향 매핑
    @OneToMany(mappedBy = "travelPlan")
    private List<TravelPlanDestinationRoute> travelPlanDestinationRoute;

    @Builder
    public TravelPlan(Long travelPlanId, String title, User user) {
        this.travelPlanId = travelPlanId;
        this.title = title;
        this.user = user;
        this.user.getTravelPlanList().add(this);
    }
}
