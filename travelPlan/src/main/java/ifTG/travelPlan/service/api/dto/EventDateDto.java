package ifTG.travelPlan.service.api.dto;

import lombok.Getter;

@Getter
public class EventDateDto {
    private String eventStartDate;
    private String eventEndDate;

    public EventDateDto(String eventStartDate, String eventEndDate) {
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }
}
