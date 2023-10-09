package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.*;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncListDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailcommon.DetailCommonDto;

import java.io.UnsupportedEncodingException;


public interface TourApi {

    String selectLocationBase(Long areaCode);

    String selectCategoryCode(ContentType contentTypeId, CatDto catDto);

    String selectAreaBased(ArrangeToTourApi arrange, ContentType contentTypeId, AreaCodeDto areaCodeDto, CatDto cat, int page);

    String selectLocationBase(ArrangeToTourApi arrange, ContentType contentTypeId, MapXY mapXY, Integer radius, int page);

    String searchKeyword(ArrangeToTourApi arrange, ContentType contentTypeId, AreaCodeDto areaCodeDto, CatDto catDto, String keyword, int page) throws UnsupportedEncodingException;

    String searchFestival(ArrangeToTourApi arrange, AreaCodeDto areaCodeDto, EventDateDto eventDate, int page);

    String searchStay(ArrangeToTourApi arrange, AreaCodeDto areaCodeDto, int page);

    DetailCommonDto selectDetailCommon(String contentId, ContentType contentTypeId);

    String selectIntroductionIntro(String contentId, ContentType contentTypeId);

    String selectDetailInfo(Long contentId, ContentType contentTypeId);

    String selectDetailImage(Long contentId);

    AreaBasedSyncListDto selectAreaBasedSynList(int page);

    String selectDetailPetTour(String contentId, int page);
}
