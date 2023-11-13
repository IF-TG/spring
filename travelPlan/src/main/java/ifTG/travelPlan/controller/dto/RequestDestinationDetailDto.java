package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
public class RequestDestinationDetailDto {
    private Long destinationId;
    private Integer contentTypeId;
    private Long userId;
}
