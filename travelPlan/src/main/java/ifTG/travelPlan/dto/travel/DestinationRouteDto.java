package ifTG.travelPlan.dto.travel;

import ifTG.travelPlan.domain.travel.Destination;
import lombok.Getter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class DestinationRouteDto {
    private final Map<DataType, Object> data = new HashMap<>();
    private String eta;


    /*public void addRouteDto(RouteDto routeDto){

    }    */

    public void addDestinationDto(DestinationDto dto, LocalDateTime eta){
        this.eta = eta.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
        data.put(DataType.DESTINATION, dto);
    }

    public DestinationDto getDestination(){
        if(data.containsKey(DataType.DESTINATION)){
            return (DestinationDto) data.get(DataType.DESTINATION);
        }
        else{
            return null;
        }
    }

    public LocalDateTime getEta(){
        return LocalDateTime.parse(this.eta, DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
    }
}