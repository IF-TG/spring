package ifTG.travelPlan.service.post;

import ifTG.travelPlan.domain.post.PostScrapId;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.post.PostDetailsWithIsScraped;
import ifTG.travelPlan.repository.springdata.post.PostScrapRepository;
import ifTG.travelPlan.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class PostDetailServiceImpl implements PostDetailService{
    private final CommentService commentService;
    private final PostViewService postViewService;
    private final PostScrapRepository postScrapRepository;

    @Override
    public PostDetailsWithIsScraped getPostDetail(Long postId, Long userId, Pageable pageable) {
        postViewService.addPostViewByPostIdAndUserId(postId, userId);
        boolean isScraped = postScrapRepository.existsById(new PostScrapId(postId, userId));
        List<CommentDtoWithUserInfo> commentDtoWithUserInfoList = commentService.getCommentListByPost(postId, userId, pageable);
        return new PostDetailsWithIsScraped(commentDtoWithUserInfoList, isScraped);
    }
}
