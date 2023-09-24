package ifTG.travelPlan.service.api.dto.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GPTMessage {
    private String role;
    private String content;
}
