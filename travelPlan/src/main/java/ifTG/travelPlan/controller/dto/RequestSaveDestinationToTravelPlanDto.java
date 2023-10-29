package ifTG.travelPlan.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class RequestSaveDestinationToTravelPlanDto {
    private Long destinationId;
    private Long travelPlanId;
    private LocalDateTime eta;

    public RequestSaveDestinationToTravelPlanDto(Long destinationId, Long travelPlanId, String eta) {
        this.destinationId = destinationId;
        this.travelPlanId = travelPlanId;
        this.eta = LocalDateTime.parse(eta, DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
    }
}
