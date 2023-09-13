package ifTG.travelPlan.service.api.dto.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GPTParameter {
    private String type;
    private Object properties;
}
