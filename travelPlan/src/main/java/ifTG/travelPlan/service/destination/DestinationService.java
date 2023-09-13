package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.dto.destination.DestinationSearchDto;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;

import java.util.List;

public interface DestinationService {
    List<EDestination> findAllByKeyword(RequestSearchDestinationDto dto);
}
