package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.dto.travel.DataType;
import ifTG.travelPlan.dto.travel.DestinationDto;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class TravelPlanDestinationIdDto {
    private Map<DataType, Long> data;
    private LocalDateTime eta;

    public TravelPlanDestinationIdDto(Map<DataType, Long> data, String eta) {
        this.data = data;
        this.eta = LocalDateTime.parse(eta, DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }


}
