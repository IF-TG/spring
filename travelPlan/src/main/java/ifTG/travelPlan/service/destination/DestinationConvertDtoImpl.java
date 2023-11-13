package ifTG.travelPlan.service.destination;


import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.destinationdetail.*;
import ifTG.travelPlan.dto.destination.*;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.service.api.dto.ContentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationConvertDtoImpl implements DestinationConvertDto {
    @Override
    public DestinationDto getDestinationDto(Destination destination, boolean isScraped){
        return DestinationDto.builder()
                              .id(destination.getId())
                              .contentType(destination.getContentType())
                              .title(destination.getTitle())
                              .address(destination.getAddress())
                              .addressDetail(destination.getAddressDetail())
                              .mapX(destination.getMapX())
                              .mapY(destination.getMapY())
                              .overview(destination.getOverview())
                              .tel(destination.getTel())
                              .category(destination.getCategory())
                              .zipcode(destination.getZipcode())
                              .thumbnail(destination.getThumbNail())
                              .isScraped(isScraped).build();
    }

    @Override
    public List<DestinationDto> getDestinationDtoListByScrap(List<Destination> destinationList){
        return destinationList.stream().map(d->getDestinationDto(d, true)).toList();
    }

    @Override
    public CulturalFacilityDto getCulturalFacilityDto(CulturalFacility culturalFacility) {
        return CulturalFacilityDto.builder()
                .culturalFacilityId(culturalFacility.getId())
                .capacity(culturalFacility.getCapacity())
                .scale(culturalFacility.getScale())
                .usageFee(culturalFacility.getUsageFee())
                .usageTime(culturalFacility.getUsageTime())
                .spendTime(culturalFacility.getSpendTime())
                .checkPet(culturalFacility.getCheckPet())
                .checkBabyStroller(culturalFacility.getCheckBabyStroller())
                .discountInfo(culturalFacility.getDiscountInfo())
                .parking(culturalFacility.getParking())
                .parkingFee(culturalFacility.getParkingFee())
                .build();
    }

    @Override
    public EventDto getEventDto(Event event) {
        return EventDto.builder()
                .eventId(event.getId())
                .sponsor(event.getSponsor())
                .usageFee(event.getUsageFee())
                .eventPlace(event.getEventPlace())
                .program(event.getProgram())
                .ageLimit(event.getAgeLimit())
                .showtime(event.getShowtime())
                .spendTime(event.getSpendTime())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }

    @Override
    public LeisureSportsDto getLeisureSportsDto(LeisureSports leisureSports) {
        return LeisureSportsDto.builder()
                .leisureSportId(leisureSports.getId())
                .capacity(leisureSports.getCapacity())
                .usageTime(leisureSports.getUsageTime())
                .openPeriod(leisureSports.getOpenPeriod())
                .parking(leisureSports.getParking())
                .parkingFee(leisureSports.getParkingFee())
                .usageFee(leisureSports.getUsageFee())
                .checkPet(leisureSports.getCheckPet())
                .recommendedAge(leisureSports.getRecommendedAge())
                .checkBabyStroller(leisureSports.getCheckBabyStroller())
                .build();
    }

    @Override
    public RestaurantDto getRestaurantDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantId(restaurant.getId())
                .featuredMenu(restaurant.getFeaturedMenu())
                .treatMenu(restaurant.getTreatMenu())
                .openDate(restaurant.getOpenDate())
                .openTime(restaurant.getOpenTime())
                .packing(restaurant.getPacking())
                .parking(restaurant.getParking())
                .restDate(restaurant.getRestDate())
                .seat(restaurant.getSeat())
                .scale(restaurant.getScale())
                .build();
    }

    @Override
    public ShoppingDto getShoppingDto(Shopping shopping) {
        return ShoppingDto.builder()
                .shoppingId(shopping.getId())
                .checkBabyStroller(shopping.getCheckBabyStroller())
                .checkPet(shopping.getCheckPet())
                .openDate(shopping.getOpenDate())
                .openTime(shopping.getOpenTime())
                .restDate(shopping.getRestDate())
                .fairDate(shopping.getFairDate())
                .saleItem(shopping.getSaleItem())
                .scale(shopping.getScale())
                .build();

    }

    @Override
    public SightSeeingDto getSightseeingDto(SightSeeing sightSeeing) {
        return SightSeeingDto.builder()
                .sightSeeingId(sightSeeing.getId())
                .capacity(sightSeeing.getCapacity())
                .experienceGuide(sightSeeing.getExperienceGuide())
                .checkBabyStroller(sightSeeing.getCheckBabyStroller())
                .restDate(sightSeeing.getRestDate())
                .usageTime(sightSeeing.getUsageTime())
                .openDate(sightSeeing.getOpenDate())
                .checkPet(sightSeeing.getCheckPet())
                .build();
    }
}
