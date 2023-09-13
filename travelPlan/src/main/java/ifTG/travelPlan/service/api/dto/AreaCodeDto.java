package ifTG.travelPlan.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AreaCodeDto {
    private Integer areaCode;
    private Integer sigunguCode;

    public AreaCodeDto(Integer areaCode, Integer sigunguCode) {
        this.areaCode = areaCode;
        if(areaCode!=null){
            this.sigunguCode = sigunguCode;
        }
    }
}
