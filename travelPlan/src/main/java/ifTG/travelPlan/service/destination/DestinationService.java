package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.elasticsearch.domain.EDestination;

import java.util.List;

public interface DestinationService {
    List<EDestination> findAllByKeyword(RequestSearchDestinationDto dto);
}
