package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.dto.destination.DestinationDetailDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.service.api.dto.ContentType;


import java.util.List;

public interface DestinationService {

    DestinationDetailDto findByDestinationId(Long destinationId, ContentType contentType, Long userId);
}
