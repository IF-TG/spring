package ifTG.travelPlan.service.api.dto;

import ifTG.travelPlan.service.api.dto.gpt.request.GPTMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChoiceDto {
    private final int index;
    private final GPTMessage message;
    private final String finish_reason;
}
