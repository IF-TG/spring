package ifTG.travelPlan.dto.travel;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Getter
public class TravelPlanDestinationDto {
    private final Map<DataType, Object> data = new HashMap<>();
    private String eta;


    /*public void addRouteDto(RouteDto routeDto){

    }    */

    public void addDestinationDto(DestinationDto dto, LocalDateTime eta){
        this.eta = eta.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        data.put(DataType.DESTINATION, dto);
    }

}
