package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.dto.DataType;
import lombok.Getter;

import java.util.Map;

@Getter
public class DestinationRouteDto {
    private DataType data_type;
    private Map<String, Object> data;

}
