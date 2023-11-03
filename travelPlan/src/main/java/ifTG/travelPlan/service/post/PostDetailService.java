package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.controller.dto.RequestCommentByPostDto;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;


public interface PostDetailService {

    PostDetailsWithIsScraped getPostDetail(RequestCommentByPostDto dto);
}
