package ifTG.travelPlan.dto.destination;

import ifTG.travelPlan.domain.travel.Destination;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter

public class EventDto {
    private Long eventId;
    private String sponsor;
    private String usageFee;
    private String eventPlace;
    private String program;
    private String ageLimit;
    private String showtime;
    private String spendTime;
    private String startDate;
    private String endDate;

    @Builder
    public EventDto(Long eventId, String sponsor, String usageFee, String eventPlace, String program, String ageLimit,
                    String showtime, String spendTime, LocalDate startDate, LocalDate endDate) {
        this.eventId = eventId;
        this.sponsor = sponsor;
        this.usageFee = usageFee;
        this.eventPlace = eventPlace;
        this.program = program;
        this.ageLimit = ageLimit;
        this.showtime = showtime;
        this.spendTime = spendTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd");
        this.startDate = startDate.format(formatter);
        this.endDate = endDate.format(formatter);
    }
}
