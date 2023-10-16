package ifTG.travelPlan.domain.travel.destinationdetail;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "destination_event")
public class Event {
    @Id
    @Column(name = "destination_event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    private Destination destination;
    private String sponsor;
    private String usageFee;
    private String eventPlace;
    private String program;
    private String ageLimit;
    private String showtime;
    private String spendTime;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public Event(Destination destination, String sponsor, String usageFee, String eventPlace, String program, String ageLimit, String showtime, String spendTime, LocalDate startDate, LocalDate endDate) {
        this.destination = destination;
        this.sponsor = sponsor;
        this.usageFee = usageFee;
        this.eventPlace = eventPlace;
        this.program = program;
        this.ageLimit = ageLimit;
        this.showtime = showtime;
        this.spendTime = spendTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
