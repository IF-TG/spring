package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;


import java.util.List;

public interface DestinationService {
    List<ResponseEDestinationDto> findAllByKeyword(RequestSearchDestinationDto dto);

}
