package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.ContentType;

public interface TourApiDetailIntro {
    Object selectIntroductionIntro(String contentId, ContentType contentTypeId);
}
