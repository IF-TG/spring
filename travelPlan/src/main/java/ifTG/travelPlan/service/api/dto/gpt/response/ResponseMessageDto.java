package ifTG.travelPlan.service.api.dto.gpt.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class ResponseMessageDto {
    private String role;
    private String content;
    private ResponseFunctionCall function_call;
    private String finishReason;

    public ResponseMessageDto(String role, String content, ResponseFunctionCall functionCall, String finishReason) {
        this.role = role;
        this.content = content;
        this.function_call = functionCall;
        this.finishReason = finishReason;
        log.info("role={}, content={}, finishReason={}", role, content, finishReason);
    }
}
