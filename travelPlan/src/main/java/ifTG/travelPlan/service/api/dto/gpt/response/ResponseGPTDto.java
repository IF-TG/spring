package ifTG.travelPlan.service.api.dto.gpt.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Slf4j
public class ResponseGPTDto {
    private String id;
    private String object;
    private Integer created;
    private String model;
    private List<ResponseChoiceDto> choices;
    private ResponseUsage usage;


    public ResponseGPTDto(String id, String object, Integer created, String model, List<ResponseChoiceDto> choices, ResponseUsage usage) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
        log.info("id = {}, object = {}, created = {}, model = {}", this.id, this.object, this.created, this.model);
    }
}
