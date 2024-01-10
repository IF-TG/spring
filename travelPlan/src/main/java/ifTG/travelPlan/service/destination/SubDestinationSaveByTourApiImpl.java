package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.destinationdetail.*;
import ifTG.travelPlan.repository.springdata.travel.sucategory.CulturalFacilityRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.LeisureSportsRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.ShoppingRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.EventRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.RestaurantRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.SightSeeingRepository;
import ifTG.travelPlan.service.api.TourApiDetailIntro;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.culturalfacility.CulturalFacilityDetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.culturalfacility.CulturalFacilityDetailIntroItem;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.event.EventDetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.event.EventDetailIntroItem;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.leisuresports.LeisureSportsDetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.leisuresports.LeisureSportsDetailIntroItem;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.restaurant.RestaurantDetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.restaurant.RestaurantDetailIntroItem;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.shopping.ShoppingDetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.shopping.ShoppingDetailIntroItem;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.sightseeing.SightSeeingDetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.sightseeing.SightSeeingDetailIntroItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SubDestinationSaveByTourApiImpl implements SubDestinationSaveByTourApi{
    private final TourApiDetailIntro tourApiDetailIntro;
    private final SightSeeingRepository sightSeeingRepository;
    private final ShoppingRepository shoppingRepository;
    private final LeisureSportsRepository leisureSportsRepository;
    private final CulturalFacilityRepository culturalFacilityRepository;
    private final EventRepository eventRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void save(List<Destination> destinationList) {
        destinationList.forEach(d->{
            switch (d.getContentType()){
                case Sightseeing -> {
                    SightSeeingDetailIntroItem item =  tourApiDetailIntro.selectIntroductionIntro(SightSeeingDetailIntroDto.class,d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    SightSeeing sightSeeing = getSightSeeing(d, item);
                    sightSeeingRepository.save(sightSeeing);
                }
                case Shopping -> {
                    ShoppingDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(ShoppingDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    Shopping shopping = getShopping(d, item);
                    shoppingRepository.save(shopping);
                }
                case LeisureSports -> {
                    LeisureSportsDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(LeisureSportsDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    LeisureSports leisureSports = getLeisureSports(d, item);
                    leisureSportsRepository.save(leisureSports);
                }
                case Cultural_Facility -> {
                    CulturalFacilityDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(CulturalFacilityDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    CulturalFacility culturalFacility = getCulturalFacility(d, item);
                    culturalFacilityRepository.save(culturalFacility);
                }
                case Event_Performance_Festival -> {
                    EventDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(EventDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    Event event = getEvent(d, item);
                    eventRepository.save(event);
                }
                case Restaurant -> {
                    RestaurantDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(RestaurantDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    Restaurant restaurant = getRestaurant(d, item);
                    restaurantRepository.save(restaurant);
                }
            }
        });
    }

    private Restaurant getRestaurant(Destination d, RestaurantDetailIntroItem item) {
        return Restaurant.builder()
                .destination(d)
                .featuredMenu(replaceByTag(item.getFirstmenu()))
                .treatMenu(replaceByTag(item.getTreatmenu()))
                .openDate(replaceByTag(item.getOpendatefood()))
                .openTime(replaceByTag(item.getOpentimefood()))
                .packing(replaceByTag(item.getPacking()))
                .parking(replaceByTag(item.getParkingfood()))
                .restDate(replaceByTag(item.getRestdatefood()))
                .seat(item.getSeat())
                .scale(replaceByTag(item.getScalefood())).build();
    }

    private Event getEvent(Destination d, EventDetailIntroItem item) {
        return Event.builder()
                .destination(d)
                .sponsor(replaceByTag(item.getSponsor1()))
                .usageFee(replaceByTag(item.getUsetimefestival()))
                .eventPlace(replaceByTag(item.getEventplace()))
                .program(replaceByTag(item.getProgram()))
                .ageLimit(replaceByTag(item.getAgelimit()))
                .showtime(replaceByTag(item.getPlaytime()))
                .spendTime(replaceByTag(item.getSpendtimefestival()))
                .startDate(LocalDate.parse(item.getEventstartdate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .endDate(LocalDate.parse(item.getEventenddate(), DateTimeFormatter.ofPattern("yyyyMMdd"))).build();
    }

    private CulturalFacility getCulturalFacility(Destination d, CulturalFacilityDetailIntroItem item) {
        return CulturalFacility.builder()
                .destination(d)
                .capacity(replaceByTag(item.getAccomcountculture()))
                .scale(replaceByTag(item.getScale()))
                .usageFee(replaceByTag(item.getUsefee()))
                .usageTime(replaceByTag(item.getUsetimeculture()))
                .spendTime(replaceByTag(item.getSpendtime()))
                .checkPet(replaceByTag(item.getChkpetculture()))
                .checkBabyStroller(replaceByTag(item.getChkbabycarriageculture()))
                .discountInfo(replaceByTag(item.getDiscountinfo()))
                .parking(replaceByTag(item.getParkingculture()))
                .parkingFee(replaceByTag(item.getParkingfee())).build();
    }

    private LeisureSports getLeisureSports(Destination d, LeisureSportsDetailIntroItem item) {
        return LeisureSports.builder()
                .destination(d)
                .capacity(replaceByTag(item.getAccomcountleports()))
                .usageTime(replaceByTag(item.getUsetimeleports()))
                .openPeriod(replaceByTag(item.getOpenperiod()))
                .parking(replaceByTag(item.getParkingleports()))
                .parkingFee(replaceByTag(item.getParkingfeeleports()))
                .usageFee(replaceByTag(item.getUsefeeleports()))
                .checkPet(replaceByTag(item.getChkpetleports()))
                .recommendedAge(replaceByTag(item.getExpagerangeleports()))
                .checkBabyStroller(replaceByTag(item.getChkbabycarriageleports())).build();
    }

    private Shopping getShopping(Destination d, ShoppingDetailIntroItem item) {
        return Shopping.builder()
                .destination(d)
                .checkBabyStroller(item.getChkbabycarriageshopping())
                .checkPet(replaceByTag(item.getChkpetshopping()))
                .openDate(replaceByTag(item.getOpendateshopping()))
                .openTime(replaceByTag(item.getOpentime()))
                .restDate(replaceByTag(item.getRestdateshopping()))
                .fairDate(replaceByTag(item.getFairday()))
                .saleItem(replaceByTag(item.getSaleitem()))
                .scale(replaceByTag(item.getScaleshopping())).build();
    }

    private SightSeeing getSightSeeing(Destination d, SightSeeingDetailIntroItem item) {
        return SightSeeing.builder()
                .destination(d)
                .capacity(replaceByTag(item.getAccomcount()))
                .experienceGuide(replaceByTag(item.getExpguide()))
                .checkBabyStroller(replaceByTag(item.getChkbabycarriage()))
                .restDate(replaceByTag(item.getRestdate()))
                .openDate(replaceByTag(item.getOpendate()))
                .usageTime(replaceByTag(item.getUsetime()))
                .checkPet(replaceByTag(item.getChkpet())).build();
    }

    private String replaceByTag(String input){
        return input.replaceAll("<\\s*/?\\s*br\\s*/?\\s*>", "\n");
    }

}
