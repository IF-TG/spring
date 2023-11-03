package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.*;
import ifTG.travelPlan.service.api.dto.tourapi.TourApiResponseDto;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncListDto;
<<<<<<< HEAD
import ifTG.travelPlan.service.api.dto.tourapi.categorycode.CategoryCodeDto;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
import ifTG.travelPlan.service.api.dto.tourapi.detailcommon.DetailCommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
<<<<<<< HEAD
import java.util.Objects;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de

@Service
@RequiredArgsConstructor
@Slf4j
public class TourApiImpl implements TourApi{
    @Value("${api.tour.url.base_url}")
    private String basedURL;
    @Value("${api.tour.secure_key}")
    private String secureKey;
    @Value("${api.tour.url.search_keyword}")
    private String searchKeywordMethod;
    @Value("${api.tour.url.location_base}")
    private String selectLocationBase;
    @Value("${api.tour.url.category_code}")
    private String selectCategoryCode;
    @Value("${api.tour.url.area_based}")
    private String selectAreaBased;
    @Value("${api.tour.url.search_festival}")
    private String searchFestival;
    @Value("${api.tour.url.search_stay}")
    private String searchStay;
    @Value("${api.tour.url.detail_common}")
    private String detailCommon;
    @Value("${api.tour.url.detail_info}")
    private String detailInfo;

    @Value("${api.tour.url.detail_image}")
    private String detailImage;
    @Value("${api.tour.url.area_based_syncList}")
    private String areaBasedSynList;
    @Value("${api.tour.url.detail_pet}")
    private String detailPet;

    private final RestTemplate restTemplate;



    @Override
    public String selectLocationBase(Long areaCode){
        String url = basedURL + searchKeywordMethod;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                .queryParam("areaCode", areaCode).build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri(), HttpMethod.GET, entity, String.class);

        return getBody(response);
    }

    @Override
<<<<<<< HEAD
    public CategoryCodeDto selectCategoryCode(ContentType contentTypeId, CatDto catDto){
=======
    public String selectCategoryCode(ContentType contentTypeId, CatDto catDto){
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
        String url = basedURL + selectCategoryCode;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("cat1", catDto.getCat1())
                .queryParam("cat2", catDto.getCat2())
                .queryParam("cat3", catDto.getCat3())
                .build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
<<<<<<< HEAD
        //log.info("tour api url = {}", builder.toUriString());

        TourApiResponseDto<CategoryCodeDto> response = restTemplate.exchange(builder.toUri(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<TourApiResponseDto<CategoryCodeDto>>(){}).getBody();

        return Objects.requireNonNull(response).getResponse().getBody().getItems();
=======
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri(), HttpMethod.GET, entity, String.class);

        return getBody(response);
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
    }

    /**
     * listYN : 목록 구분 ( Y : 목록 , N : 개수 )
     * arrange : 정렬 기준 ( A : 제목순 C : 수정일순 D : 생성일순 , ( 대표이미지가 있는 ) O : 제목순 Q : 수정일순 R : 생선일순 )
     * areaCode : 지역 코드
     * sigunguCode : 시군구 코드 ( areaCode 필수 )
     * cat1 : 카테고리
     * cat2 : 중분류
     * cat3 : 소분류
     */
    @Override
    public String selectAreaBased(ArrangeToTourApi arrange, ContentType contentTypeId, AreaCodeDto areaCodeDto, CatDto cat, int page){
        String url = basedURL + selectAreaBased;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), page)
                .queryParam("arrange", arrange)
                .queryParam("areaCode", areaCodeDto.getAreaCode())
                .queryParam("sigunguCode", areaCodeDto.getSigunguCode())
                .queryParam("cat1", cat.getCat1())
                .queryParam("cat2", cat.getCat2())
                .queryParam("cat3", cat.getCat3())
                .build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri() , HttpMethod.GET, entity, String.class);

        return getBody(response);
    }

    @Override
    public String selectLocationBase(ArrangeToTourApi arrange, ContentType contentTypeId, MapXY mapXY, Integer radius, int page){
        String url = basedURL + selectLocationBase;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), page)
                .queryParam("arrange", arrange)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("mapX", mapXY.getMapX())
                .queryParam("mapY", mapXY.getMapY())
                .queryParam("radius", radius)
                .build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri() , HttpMethod.GET, entity, String.class);

        return getBody(response);
    }


    @Override
    public String searchKeyword(ArrangeToTourApi arrange, ContentType contentTypeId, AreaCodeDto areaCodeDto, CatDto catDto, String keyword, int page) throws UnsupportedEncodingException {
        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        String url = basedURL + searchKeywordMethod;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), page)
                .queryParam("keyword", keyword)
                .queryParam("arrange", arrange)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("areaCode", areaCodeDto.getAreaCode())
                .queryParam("sigunguCode", areaCodeDto.getSigunguCode())
                .queryParam("cat1", catDto.getCat1())
                .queryParam("cat2", catDto.getCat2())
                .queryParam("cat3", catDto.getCat3())
                .build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri() , HttpMethod.GET, entity, String.class);

        return getBody(response);
    }

    @Override
    public String searchFestival(ArrangeToTourApi arrange, AreaCodeDto areaCodeDto, EventDateDto eventDate, int page){
        String url = basedURL + searchFestival;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), page)
                .queryParam("arrange", arrange)
                .queryParam("areaCode", areaCodeDto.getAreaCode())
                .queryParam("sigunguCode", areaCodeDto.getSigunguCode())
                .queryParam("eventStartDate", eventDate.getEventStartDate())
                .queryParam("eventEndDate", eventDate.getEventEndDate())
                .build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri() , HttpMethod.GET, entity, String.class);

        return getBody(response);
    }

    @Override
    public String searchStay(ArrangeToTourApi arrange, AreaCodeDto areaCodeDto, int page){
        String url = basedURL + searchStay;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), page)
                .queryParam("arrange", arrange)
                .queryParam("areaCode", areaCodeDto.getAreaCode())
                .queryParam("sigunguCode", areaCodeDto.getSigunguCode())
                .build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        log.info("tour api url = {}", builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUri() , HttpMethod.GET, entity, String.class);

        return getBody(response);
    }




    @Override
    public String selectDetailImage(Long contentId){
        String url = basedURL + detailImage;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 1)
                .queryParam("contentId", contentId)
                .queryParam("imageYN", "Y")
                .queryParam("subImageYN","Y")
                .build(true);
        log.info("tour api url = {}", builder.toUriString());


        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(builder.toUri(), HttpMethod.GET, entity, String.class);

        return getBody(response);
    }

    @Override
    public AreaBasedSyncListDto selectAreaBasedSynList(int page){
        String url = basedURL + areaBasedSynList;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), page)
                .queryParam("showflag", 1)
                //.queryParam("contentTypeId", ContentType.Cultural_Facility.getValue())
                .build(true);
        log.info("tour api url = {}", builder.toUriString());

        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

        TourApiResponseDto<AreaBasedSyncListDto> response = restTemplate.exchange(
                builder.toUri(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<TourApiResponseDto<AreaBasedSyncListDto>>(){}
        ).getBody();

        if (response==null)throw new RuntimeException("Fail Connect to Tour API");

        return response.getResponse().getBody().getItems();
    }
    @Override
    public DetailCommonDto selectDetailCommon(Long contentId, ContentType contentTypeId){
        String url = basedURL + detailCommon;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                .queryParam("contentId", contentId)
                .queryParam("contentTypeId", contentTypeId.getValue())
                .queryParam("defaultYN", "N")
                .queryParam("firstImageYN", "Y")
                .queryParam("areacodeYN", "Y")
                .queryParam("catcodeYN","Y")
                .queryParam("addrinfoYN", "Y")
                .queryParam("overviewYN", "Y")
                .build(true);
        log.info("tour api url = {}", builder.toUriString());

        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        TourApiResponseDto<DetailCommonDto> response = restTemplate.exchange(
                builder.toUri(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<TourApiResponseDto<DetailCommonDto>>() {}).getBody();

        if (response==null)throw new RuntimeException("Fail Connect to Tour API");

        return response.getResponse().getBody().getItems();

    }
    @Override
    public Object selectDetailInfo(String contentId, ContentType contentTypeId){
        String url = basedURL + detailInfo;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                                       .queryParam("contentId", contentId)
                                       .queryParam("contentTypeId", contentTypeId.getValue())
                                       .build(true);
        log.info("tour api url = {}", builder.toUriString());


        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        TourApiResponseDto<Object> response = restTemplate.exchange(builder.toUri(), HttpMethod.GET, entity,
                new ParameterizedTypeReference<TourApiResponseDto<Object>>() {}).getBody();

        return response.getResponse().getBody().getItems();
    }
    @Override
    public String selectDetailPetTour(String contentId, int page){
        String url = basedURL + detailPet;
        UriComponents builder = getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                .queryParam("contentId", contentId)
                .build(true);
        log.info("tour api url = {}", builder.toUriString());

        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(builder.toUri(), HttpMethod.GET, entity, String.class);

        log.info("{}", response.getBody());
        return getBody(response);
    }

    @Override
    public UriComponentsBuilder getUriComponentToTourApi(UriComponentsBuilder uriComponentsBuilder, int page){
        return uriComponentsBuilder
<<<<<<< HEAD
                .queryParam("numOfRows", 50)
=======
<<<<<<< HEAD
                .queryParam("numOfRows", 20)
=======
                .queryParam("numOfRows", 10)
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
>>>>>>> bf6ed99a14e3d427dc70dda0a2faf472b70ae599
                .queryParam("pageNo", page)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "travelPlan")
                .queryParam("_type", "json")
                .queryParam("serviceKey", secureKey);
    }

    private static String getBody(ResponseEntity<String> response) {
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else{
            throw  new RuntimeException("Tour API 호출에 실패했습니다.");
        }
    }

}
