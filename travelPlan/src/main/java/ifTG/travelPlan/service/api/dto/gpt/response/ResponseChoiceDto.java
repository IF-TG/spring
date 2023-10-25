package ifTG.travelPlan.service.api.dto.gpt.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class ResponseChoiceDto {
    private Integer index;
    private ResponseMessageDto message;

    public ResponseChoiceDto(Integer index, ResponseMessageDto message){
        this.index = index;
        this.message = message;
        log.info("index = {}", index);
    }
}
