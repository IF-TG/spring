package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.DestinationLikeId;
import ifTG.travelPlan.domain.travel.DestinationScrapId;
import ifTG.travelPlan.domain.travel.destinationdetail.*;
import ifTG.travelPlan.dto.destination.*;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.repository.querydsl.QDestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationLikeRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.service.api.dto.ContentType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DestinationServiceImpl implements DestinationService{

    private final DestinationScrapRepository destinationScrapRepository;
    private final DestinationLikeRepository destinationLikeRepository;
    private final QDestinationRepository qDestinationRepository;
    private final DestinationConvertDto destinationConvertDto;

    @Override
    public DestinationDetailDto findByDestinationId(Long destinationId, ContentType contentType, Long userId) {
        Object detailWithDestination = qDestinationRepository.findDetailWithDestinationById(destinationId, contentType);
        boolean isScraped = destinationScrapRepository.existsById(new DestinationScrapId(destinationId, userId));
        boolean isLiked = destinationLikeRepository.existsById(new DestinationLikeId(destinationId, userId));
        return getDetailDto(detailWithDestination, contentType, isLiked, isScraped);
    }

    private DestinationDetailDto getDetailDto(Object object, ContentType contentType, boolean isLiked, boolean isScraped) {
        DestinationDetailDto dto = null;
        switch (contentType){
            case Cultural_Facility -> dto = getCulturalFacilityWithDestinationDto((CulturalFacility) object, isLiked, isScraped);
            case Event_Performance_Festival -> dto = getEventWithDestinationDto((Event)object, isLiked, isScraped);
            case LeisureSports -> dto = getLeisureSportsWithDestinationDto((LeisureSports) object, isLiked, isScraped);
            case Restaurant -> dto = getRestaurantWithDestinationDto((Restaurant) object, isLiked, isScraped);
            case Shopping -> dto = getShoppingWithDestinationDto((Shopping) object, isLiked, isScraped);
            case Sightseeing -> dto = getSightseeingWithDestinationDto((SightSeeing) object, isLiked, isScraped);
        }
        return dto;
    }

    private DestinationDetailDto getSightseeingWithDestinationDto(SightSeeing sightseeing, boolean isLiked, boolean isScraped){
        DestinationDto destinationDto = destinationConvertDto.getDestinationDto(sightseeing.getDestination(), isScraped);
        SightSeeingDto sightseeingDto = destinationConvertDto.getSightseeingDto(sightseeing);
        return new DestinationDetailDto(destinationDto, sightseeingDto, isLiked);
    }
    private DestinationDetailDto getShoppingWithDestinationDto(Shopping shopping, boolean isLiked, boolean isScraped){
        DestinationDto destinationDto = destinationConvertDto.getDestinationDto(shopping.getDestination(), isScraped);
        ShoppingDto shoppingDto = destinationConvertDto.getShoppingDto(shopping);
        return new DestinationDetailDto(destinationDto, shoppingDto, isLiked);
    }
    private DestinationDetailDto getRestaurantWithDestinationDto(Restaurant restaurant, boolean isLiked, boolean isScraped){
        DestinationDto destinationDto = destinationConvertDto.getDestinationDto(restaurant.getDestination(), isScraped);
        RestaurantDto restaurantDto = destinationConvertDto.getRestaurantDto(restaurant);
        return new DestinationDetailDto(destinationDto, restaurantDto, isLiked);
    }

    private DestinationDetailDto getLeisureSportsWithDestinationDto(LeisureSports leisureSports, boolean isLiked, boolean isScraped){
        DestinationDto destinationDto = destinationConvertDto.getDestinationDto(leisureSports.getDestination(), isScraped);
        LeisureSportsDto leisureSportsDto = destinationConvertDto.getLeisureSportsDto(leisureSports);
        return new DestinationDetailDto(destinationDto, leisureSportsDto, isLiked);
    }

    private DestinationDetailDto getEventWithDestinationDto(Event event, boolean isLiked, boolean isScraped) {
        DestinationDto destinationDto = destinationConvertDto.getDestinationDto(event.getDestination(), isScraped);
        EventDto eventDto = destinationConvertDto.getEventDto(event);
        return new DestinationDetailDto(destinationDto, eventDto, isLiked);
    }

    private DestinationDetailDto getCulturalFacilityWithDestinationDto(CulturalFacility culturalFacility, boolean isLiked, boolean isScraped){
        DestinationDto destinationDto = destinationConvertDto.getDestinationDto(culturalFacility.getDestination(), isScraped);
        CulturalFacilityDto culturalFacilityDto = destinationConvertDto.getCulturalFacilityDto(culturalFacility);
        return new DestinationDetailDto(destinationDto, culturalFacilityDto, isLiked);
    }


}
