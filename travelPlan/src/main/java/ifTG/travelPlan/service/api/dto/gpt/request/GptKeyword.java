package ifTG.travelPlan.service.api.dto.gpt.request;

import ifTG.travelPlan.service.api.dto.gpt.request.GptItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GptKeyword {
    private String type;
    private GptItem items;
}
