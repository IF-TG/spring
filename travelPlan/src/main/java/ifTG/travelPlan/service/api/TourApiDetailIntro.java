package ifTG.travelPlan.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import ifTG.travelPlan.service.api.dto.ContentType;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.DetailIntroDto;

public interface TourApiDetailIntro {
    <T extends DetailIntroDto> T selectIntroductionIntro(Class<T> type, Long contentId, ContentType contentTypeId);
}
