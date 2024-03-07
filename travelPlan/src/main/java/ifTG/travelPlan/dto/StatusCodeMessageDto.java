package ifTG.travelPlan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCodeMessageDto {
    private String message;
    private String code;
}
