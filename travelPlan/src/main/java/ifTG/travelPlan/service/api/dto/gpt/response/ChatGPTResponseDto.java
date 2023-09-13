package ifTG.travelPlan.service.api.dto.gpt.response;

import ifTG.travelPlan.service.api.dto.ChoiceDto;
import ifTG.travelPlan.service.api.dto.UsageDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ChatGPTResponseDto {
    private final String id;
    private final String object;
    private final Long created;
    private final String mode;
    private final List<ChoiceDto> choices;
    private final UsageDto usageDto;
}
