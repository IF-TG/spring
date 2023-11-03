package ifTG.travelPlan;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.destinationdetail.*;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.dto.travel.enums.Category;
import ifTG.travelPlan.dto.travel.enums.LargeCategory;
import ifTG.travelPlan.dto.travel.enums.MiddleCategory;
import ifTG.travelPlan.dto.travel.enums.SmallCategory;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.repository.springdata.CulturalFacilityRepository;
import ifTG.travelPlan.repository.springdata.LeisureSportsRepository;
import ifTG.travelPlan.repository.springdata.ShoppingRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.EventRepository;
import ifTG.travelPlan.repository.springdata.travel.RestaurantRepository;
import ifTG.travelPlan.repository.springdata.travel.SightSeeingRepository;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.TourApi;
import ifTG.travelPlan.service.api.TourApiDetailIntro;
import ifTG.travelPlan.service.api.dto.CatDto;

import ifTG.travelPlan.service.api.dto.ContentType;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncListDto;
import ifTG.travelPlan.service.api.dto.tourapi.categorycode.CategoryCodeDto;
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
import ifTG.travelPlan.service.travelplan.TextRank;
import ifTG.travelPlan.service.travelplan.Word2Vec;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostConstructor {
    private final PostRepository postRepository;
    private final TourApi tourApi;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final EDestinationRepository eDestinationRepository;
    private final TourApiDetailIntro tourApiDetailIntro;
    private final DestinationRepository destinationRepository;
    private final SightSeeingRepository sightSeeingRepository;
    private final ShoppingRepository shoppingRepository;
    private final LeisureSportsRepository leisureSportsRepository;
    private final CulturalFacilityRepository culturalFacilityRepository;
    private final EventRepository eventRepository;
    private final RestaurantRepository restaurantRepository;
    private final TextRank textRank;
    private final Word2Vec word2Vec;
    private final ResourceLoader resourceLoader;

    @PostConstruct
    @Transactional
    public void initData() throws IOException {
        /*addData();
        List<Destination> destinationList = null;
        for (int i  =1;i<10; i++) {
            destinationList = saveDestinationList(i);
            getSubDestination(destinationList);
        }
        word2Vec.initData();
        saveElasticDestination(destinationList);*/
    }

    private void saveElasticDestination(List<Destination> destinationList) {
        destinationList.forEach(d->{
            List<String> keywordList = textRank.textRank(d.getOverview());
            EDestination eDestination = EDestination.builder()
                                                    .id(d.getId())
                                                    .title(d.getTitle())
                                                    .keywordList(keywordList)
                                                    .thumbnailUrl(d.getThumbNail())
                                                    .info(d.getOverview())
                                                    .address(d.getAddress())
                                                    .category(d.getCategory())
                                                    .build();
            eDestinationRepository.save(eDestination);
        });
    }

    private void getSubDestination(List<Destination> destinationList) {
        destinationList.forEach(d->{
            switch (d.getContentType()){
                case Sightseeing -> {
                    SightSeeingDetailIntroItem item =  tourApiDetailIntro.selectIntroductionIntro(SightSeeingDetailIntroDto.class,d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    SightSeeing sightSeeing = SightSeeing.builder()
                                                         .destination(d)
                                                         .capacity(replaceBrTag(item.getAccomcount()))
                                                         .experienceGuide(replaceBrTag(item.getExpguide()))
                                                         .checkBabyStroller(replaceBrTag(item.getChkbabycarriage()))
                                                         .restDate(replaceBrTag(item.getRestdate()))
                                                         .openDate(replaceBrTag(item.getOpendate()))
                                                         .usageTime(replaceBrTag(item.getUsetime()))
                                                         .checkPet(replaceBrTag(item.getChkpet())).build();
                    sightSeeingRepository.save(sightSeeing);
                }
                case Shopping -> {
                    ShoppingDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(ShoppingDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    Shopping shopping = Shopping.builder()
                                                .destination(d)
                                                .checkBabyStroller(item.getChkbabycarriageshopping())
                                                .checkPet(replaceBrTag(item.getChkpetshopping()))
                                                .openDate(replaceBrTag(item.getOpendateshopping()))
                                                .openTime(replaceBrTag(item.getOpentime()))
                                                .restDate(replaceBrTag(item.getRestdateshopping()))
                                                .fairDate(replaceBrTag(item.getFairday()))
                                                .saleItem(replaceBrTag(item.getSaleitem()))
                                                .scale(replaceBrTag(item.getScaleshopping())).build();
                    shoppingRepository.save(shopping);
                }
                case Recreation -> {
                    LeisureSportsDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(LeisureSportsDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    LeisureSports leisureSports = LeisureSports.builder()
                                                               .destination(d)
                                                               .capacity(replaceBrTag(item.getAccomcountleports()))
                                                               .usageTime(replaceBrTag(item.getUsetimeleports()))
                                                               .openPeriod(replaceBrTag(item.getOpenperiod()))
                                                               .parking(replaceBrTag(item.getParkingleports()))
                                                               .parkingFee(replaceBrTag(item.getParkingfeeleports()))
                                                               .usageFee(replaceBrTag(item.getUsefeeleports()))
                                                               .checkPet(replaceBrTag(item.getChkpetleports()))
                                                               .recommendedAge(replaceBrTag(item.getExpagerangeleports()))
                                                               .checkBabyStroller(replaceBrTag(item.getChkbabycarriageleports())).build();
                    leisureSportsRepository.save(leisureSports);
                }
                case Cultural_Facility -> {
                    CulturalFacilityDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(CulturalFacilityDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    CulturalFacility culturalFacility = CulturalFacility.builder()
                                                                        .destination(d)
                                                                        .capacity(replaceBrTag(item.getAccomcountculture()))
                                                                        .scale(replaceBrTag(item.getScale()))
                                                                        .usageFee(replaceBrTag(item.getUsefee()))
                                                                        .usageTime(replaceBrTag(item.getUsetimeculture()))
                                                                        .spendTime(replaceBrTag(item.getSpendtime()))
                                                                        .checkPet(replaceBrTag(item.getChkpetculture()))
                                                                        .checkBabyStroller(replaceBrTag(item.getChkbabycarriageculture()))
                                                                        .discountInfo(replaceBrTag(item.getDiscountinfo()))
                                                                        .parking(replaceBrTag(item.getParkingculture()))
                                                                        .parkingFee(replaceBrTag(item.getParkingfee())).build();
                    culturalFacilityRepository.save(culturalFacility);
                }
                case Event_Performance_Festival -> {
                    EventDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(EventDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    Event event = Event.builder()
                                       .destination(d)
                                       .sponsor(replaceBrTag(item.getSponsor1()))
                                       .usageFee(replaceBrTag(item.getUsetimefestival()))
                                       .eventPlace(replaceBrTag(item.getEventplace()))
                                       .program(replaceBrTag(item.getProgram()))
                                       .ageLimit(replaceBrTag(item.getAgelimit()))
                                       .showtime(replaceBrTag(item.getPlaytime()))
                                       .spendTime(replaceBrTag(item.getSpendtimefestival()))
                                       .startDate(LocalDate.parse(item.getEventstartdate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                                       .endDate(LocalDate.parse(item.getEventenddate(), DateTimeFormatter.ofPattern("yyyyMMdd"))).build();
                    eventRepository.save(event);
                }
                case Restaurant -> {
                    RestaurantDetailIntroItem item = tourApiDetailIntro.selectIntroductionIntro(RestaurantDetailIntroDto.class, d.getTourApiContentId(), d.getContentType()).getItem().get(0);
                    Restaurant restaurant = Restaurant.builder()
                                                      .destination(d)
                                                      .featuredMenu(replaceBrTag(item.getFirstmenu()))
                                                      .treatMenu(replaceBrTag(item.getTreatmenu()))
                                                      .openDate(replaceBrTag(item.getOpendatefood()))
                                                      .openTime(replaceBrTag(item.getOpentimefood()))
                                                      .packing(replaceBrTag(item.getPacking()))
                                                      .parking(replaceBrTag(item.getParkingfood()))
                                                      .restDate(replaceBrTag(item.getRestdatefood()))
                                                      .seat(item.getSeat())
                                                      .scale(replaceBrTag(item.getScalefood())).build();
                    restaurantRepository.save(restaurant);
                }
            }
        });
    }

    private List<Destination> saveDestinationList(int page) {

        List<Destination> destinationList = new ArrayList<>();
        AreaBasedSyncListDto areaBasedSyncListDto = tourApi.selectAreaBasedSynList(page);
        areaBasedSyncListDto.getItem().stream().filter(absi->
                !(ContentType.Travel_Course==ContentType.getContentType(absi.getContenttypeid()).orElseThrow(()->new NoResultException("적합한 ContentTypeId를 찾을 수 없습니다."))
                        ||ContentType.Accommodation==ContentType.getContentType(absi.getContenttypeid()).orElseThrow(()->new NoResultException("적합한 ContentTypeId를 찾을 수 없습니다.")))
        ).forEach(absi->
                destinationList.add(Destination.builder()
                                               .tourApiContentId(Long.parseLong(absi.getContentid()))
                                               .contentType(ContentType.getContentType(absi.getContenttypeid()).orElseThrow(()->new NoResultException("적합한 ContentTypeId를 찾을 수 없습니다.")))
                                               .address(absi.getAddr1())
                                               .addressDetail(absi.getAddr2())
                                               .thumbNail(absi.getFirstimage2())
                                               .title(absi.getTitle())
                                               .areaCode(Integer.parseInt(absi.getAreacode()))
                                               .mapX(absi.getMapx())
                                               .mapY(absi.getMapy())
                                               .zipcode(absi.getZipcode())
                                               .mLevel(absi.getMlevel())
                                               .tel(replaceBrTag(absi.getTel()))
                                               .category(new Category(
                                                       LargeCategory.findByCode(absi.getCat1()),
                                                       MiddleCategory.findByCode(absi.getCat2()),
                                                       SmallCategory.findByCode(absi.getCat3())))
                                               .build())

        );

        destinationList.forEach(d->
                d.insertOverViewAtTourApiDetailCommon(replaceBrTag(tourApi.selectDetailCommon(d.getTourApiContentId(), d.getContentType()).getItem().get(0).getOverview()))
        );
        destinationRepository.saveAll(destinationList);
        return destinationList;
    }
    private String replaceBrTag(String input){
        return input.replaceAll("<\\s*/?\\s*br\\s*/?\\s*>", "\n");
    }
    private void addData() {
        UserAddress userAddressA = UserAddress.builder()
                                              .sido("서울")
                                              .sigungu("강남")
                                              .eupmyendong("판교")
                                              .roadName("반포자이")
                                              .buildingNumber("34907")
                                              .zipCode("34907")
                                              .subBuildingNumber("63")
                                              .build();
        User userA = User.builder()
                         .userId("dlaruddhks99")
                         .pw("dla10241024@")
                         .name("케인")
                         .sex(Sex.FEMALE)
                         .birthDate(LocalDate.of(1944, 12, 12))
                         .phoneNumber("010-1234-5678")
                         .email("kimdo@naver.com")
                         .nickname("kaneTV")
                         .userAddress(userAddressA)
                         .build();

        UserAddress userAddressB = UserAddress.builder()
                                              .sido("대전")
                                              .sigungu("중구")
                                              .eupmyendong("오류동")
                                              .roadName("계룡로 852")
                                              .buildingNumber("34907")
                                              .zipCode("34907")
                                              .subBuildingNumber("31동 605호")
                                              .build();

        User userB = User.builder()
                         .userId("ruddhks99")
                         .pw("r")
                         .name("임경완")
                         .sex(Sex.MALE)
                         .birthDate(LocalDate.of(1999, 10, 24))
                         .phoneNumber("010-6768-7937")
                         .email("ruddhks00@gmail.com")
                         .nickname("moondoooo")
                         .userAddress(userAddressB)
                         .build();

        userAddressRepository.saveAll(Arrays.asList(userAddressA, userAddressB));
        userRepository.saveAll(Arrays.asList(userA, userB));

        /**
         * postA 정보
         * Like : 1개
         * Theme : 현장 체험, 모험
         * Region : 경기
         * Season : 가을
         * Companion : 혼자
         */
        Post postA = Post.builder()
                         .title("나는! 장품을! 했다!")
                         .content("이 케인님을 뭘로 보고!")
                         .user(userB)
                         .startDate(LocalDate.of(2022, 10, 24))
                         .endDate(LocalDate.of(2022, 10, 25))
                         .build();
        log.info("postA persist");
        postRepository.save(postA);


        /**
         * Post B 정보
         * Like : 2개
         * Theme : 축제
         * Region : 대전
         * Companion : 친구들, 펫
         * season : 여름
         */
        Post postB = Post.builder()
                         .title("제발 좀 되라")
                         .content("테스트 코드 짜는게 더 힘들어 ㅁㅊ")
                         .user(userA)
                         .startDate(LocalDate.of(2023, 6, 30))
                         .endDate(LocalDate.of(2022, 7, 2)).build();
        postRepository.save(postB);


        /**
         * post C 정보
         * Like : 1개
         * companion : 가족
         * season : 겨울
         * 지역 : 충남
         */
        Post postC = Post.builder()
                         .title("차라투스트라는 이렇게 말했다.")
                         .content("낙타 개시끄럽네")
                         .user(userB)
                         .startDate(LocalDate.now())
                         .endDate(LocalDate.now()).build();
        postRepository.save(postC);


        /**
         * postD 정보
         */
        Post postD = Post.builder()
                         .title("게이는 문화다. 게이는 문화다. 게이는 문화다")
                         .content("게이조이고")
                         .user(userB)
                         .startDate(LocalDate.now())
                         .endDate(LocalDate.now()).build();
        postRepository.save(postD);
    }

}