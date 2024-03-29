package ifTG.travelPlan.domain.travel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "travel_plan_destination")
@Getter
@ToString
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
    public TravelPlanDestination(Long travelPlanId, Long destinationId, LocalDateTime eta) {
        this.id = new TravelPlanDestinationId(travelPlanId, destinationId);
        this.eta = eta;
    }

    public void updateTravelPlanDestination(LocalDateTime eta){
        this.eta = eta;
    }
}