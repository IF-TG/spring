package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;

import java.util.List;

public interface EDestinationConvertDtoService {
    List<ResponseEDestinationDto> getResponseEDestinationDtoList(boolean isGptRelated, List<EDestination> eDestinationList, List<Long> destinationScrapIdList);
}
