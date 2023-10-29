package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.dto.post.PostDetailsWithImagesAndCommentsDto;


public interface PostDetailService {

    public PostDetailsWithImagesAndCommentsDto getPostDetail(RequestCommentAndImgsDto dto);
}
