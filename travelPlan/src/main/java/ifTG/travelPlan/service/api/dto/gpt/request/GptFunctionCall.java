package ifTG.travelPlan.service.api.dto.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GptFunctionCall {
    private final String name;
}
