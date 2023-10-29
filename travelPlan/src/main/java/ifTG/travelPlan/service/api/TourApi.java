package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.*;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncListDto;
<<<<<<< HEAD
import ifTG.travelPlan.service.api.dto.tourapi.categorycode.CategoryCodeDto;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
import ifTG.travelPlan.service.api.dto.tourapi.detailcommon.DetailCommonDto;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;


public interface TourApi {

    String selectLocationBase(Long areaCode);

<<<<<<< HEAD
    CategoryCodeDto selectCategoryCode(ContentType contentTypeId, CatDto catDto);
=======
    String selectCategoryCode(ContentType contentTypeId, CatDto catDto);
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de

    String selectAreaBased(ArrangeToTourApi arrange, ContentType contentTypeId, AreaCodeDto areaCodeDto, CatDto cat, int page);

    String selectLocationBase(ArrangeToTourApi arrange, ContentType contentTypeId, MapXY mapXY, Integer radius, int page);

    String searchKeyword(ArrangeToTourApi arrange, ContentType contentTypeId, AreaCodeDto areaCodeDto, CatDto catDto, String keyword, int page) throws UnsupportedEncodingException;

    String searchFestival(ArrangeToTourApi arrange, AreaCodeDto areaCodeDto, EventDateDto eventDate, int page);

    String searchStay(ArrangeToTourApi arrange, AreaCodeDto areaCodeDto, int page);

    DetailCommonDto selectDetailCommon(Long contentId, ContentType contentTypeId);

    String selectDetailImage(Long contentId);

    AreaBasedSyncListDto selectAreaBasedSynList(int page);

    Object selectDetailInfo(String contentId, ContentType contentTypeId);

    String selectDetailPetTour(String contentId, int page);

    UriComponentsBuilder getUriComponentToTourApi(UriComponentsBuilder uriComponentsBuilder, int page);
}
