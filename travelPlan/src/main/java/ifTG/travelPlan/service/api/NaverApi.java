package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.naver.NaverBlogApiDto;

public interface NaverApi {
    NaverBlogApiDto selectBlogInfo(String query);
}
