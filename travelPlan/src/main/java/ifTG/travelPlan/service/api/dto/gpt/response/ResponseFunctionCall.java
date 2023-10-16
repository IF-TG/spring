package ifTG.travelPlan.service.api.dto.gpt.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class ResponseFunctionCall {
    private String name;
    private String arguments;

    public ResponseFunctionCall(String name, String arguments) throws JsonProcessingException {
        this.name = name;
        this.arguments = arguments;
    }
}
