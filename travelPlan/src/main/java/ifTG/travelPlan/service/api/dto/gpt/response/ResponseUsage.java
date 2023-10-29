package ifTG.travelPlan.service.api.dto.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUsage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
}
