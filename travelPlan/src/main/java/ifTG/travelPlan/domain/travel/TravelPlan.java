package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.diary.Diary;
import ifTG.travelPlan.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Getter
@Table(name = "travel_plans")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlan {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "travel_plan_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //양방향 매핑
    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.REMOVE)
    private final List<TravelPlanDestination> travelPlanDestinationList = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan")
    private final List<Diary> dairyList = new ArrayList<>();

    @Builder
    public TravelPlan(String title, User user) {
        this.title = title;
        this.user = user;
        this.user.getTravelPlanList().add(this);
    }

    public void updateTravelPlan(String title) {
        this.title = title;
    }
}
