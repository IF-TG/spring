package ifTG.travelPlan.service.api.dto.gpt.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
@NoArgsConstructor
public class ResponseArgument {
    private List<String> keywords;

    public ResponseArgument(List<String> keywords){
        this.keywords = keywords;
    }
}
