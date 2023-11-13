package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.destinationdetail.*;
import ifTG.travelPlan.dto.destination.*;
import ifTG.travelPlan.dto.travel.DestinationDto;

import java.util.List;

public interface DestinationConvertDto {
    DestinationDto getDestinationDto(Destination destination, boolean isScraped);

    List<DestinationDto> getDestinationDtoListByScrap(List<Destination> destinationList);

    CulturalFacilityDto getCulturalFacilityDto(CulturalFacility culturalFacility);

    EventDto getEventDto(Event event);
    LeisureSportsDto getLeisureSportsDto(LeisureSports leisureSports);

    RestaurantDto getRestaurantDto(Restaurant restaurant);

    ShoppingDto getShoppingDto(Shopping shopping);

    SightSeeingDto getSightseeingDto(SightSeeing sightSeeing);
}
