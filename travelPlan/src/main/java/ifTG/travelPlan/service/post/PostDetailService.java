package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.dto.post.PostDetailsWithImagesAndCommentsDto;
import ifTG.travelPlan.service.comment.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface PostDetailService {

    public PostDetailsWithImagesAndCommentsDto getPostDetail(RequestCommentAndImgsDto dto);
}
