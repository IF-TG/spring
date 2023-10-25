package ifTG.travelPlan.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ToggleDto {
    private Long objectId;
    private Boolean value;
}
