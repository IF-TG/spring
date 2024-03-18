package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;

import java.util.List;

public interface PostSearchService {
    List<PostDto> findAllLikeKeyword(RequestSearchPostDto requestSearchPostDto);

    List<PostDto> findAllLikeKeyword(String keyword, boolean isTitle, boolean isContent, int page, int perPage);
}
