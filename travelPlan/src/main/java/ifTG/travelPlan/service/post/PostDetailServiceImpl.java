package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.controller.dto.RequestCommentByPostDto;
import ifTG.travelPlan.domain.post.PostScrapId;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;
import ifTG.travelPlan.repository.springdata.PostScrapRepository;
import ifTG.travelPlan.service.comment.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PostDetailServiceImpl implements PostDetailService{
    private final CommentService commentService;
    private final PostViewService postViewService;
    private final PostScrapRepository postScrapRepository;

    @Override
    public PostDetailsWithIsScraped getPostDetail(RequestCommentByPostDto dto) {
        postViewService.addPostViewByPostIdAndUserId(dto.getPostId(), dto.getUserId());
        boolean isScraped = postScrapRepository.existsById(new PostScrapId(dto.getPostId(), dto.getUserId()));
        List<CommentDtoWithUserInfo> commentDtoWithUserInfoList = commentService.getCommentListByPost(dto);
        return new PostDetailsWithIsScraped(commentDtoWithUserInfoList, isScraped);
    }
}
