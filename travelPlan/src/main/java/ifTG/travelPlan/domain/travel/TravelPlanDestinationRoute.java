package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.diary.Diary;
import ifTG.travelPlan.domain.travel.destinationroute.DestinationRoute;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "travel_plan_destination_route")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlanDestinationRoute {
    @EmbeddedId
    @Column(name = "travel_plan_destination_route_id")
    private TravelPlanDestinationRouteId id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "travel_plan_id", insertable=false, updatable=false)
    private TravelPlan travelPlan;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "destination_route_id", insertable=false, updatable=false)
    private DestinationRoute destinationRoute;

    @Column(nullable = false)
    private int order;

    //양방향 매핑
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "diary_id")
    private Diary dairy;
}