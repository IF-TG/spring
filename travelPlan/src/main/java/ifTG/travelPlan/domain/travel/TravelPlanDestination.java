package ifTG.travelPlan.domain.travel;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "travel_plan_destination")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlanDestination {
    @EmbeddedId
    @Column(name = "travel_plan_destination_id")
    private TravelPlanDestinationId id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "travel_plan_id", insertable=false, updatable=false)
    private TravelPlan travelPlan;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private Destination destination;

    @Column(nullable = false)
    private LocalDateTime eta;

    @Builder
    public TravelPlanDestination(TravelPlan travelPlan, Destination destination, LocalDateTime eta) {
        this.travelPlan = travelPlan;
        this.destination = destination;
        this.eta = eta;
    }
}