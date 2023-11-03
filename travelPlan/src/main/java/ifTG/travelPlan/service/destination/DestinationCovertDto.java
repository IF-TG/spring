package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.dto.travel.DestinationDto;

import java.util.List;

public interface DestinationCovertDto {
    DestinationDto getDestinationDto(Destination destination, boolean isScraped);

    List<DestinationDto> getDestinationDtoListByScrap(List<Destination> destinationList);
}
