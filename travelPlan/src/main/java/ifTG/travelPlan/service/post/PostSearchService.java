package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;

import java.util.List;

public interface PostSearchService {
    List<PostDto> findAllLikeKeyword(RequestSearchPostDto requestSearchPostDto);
}
