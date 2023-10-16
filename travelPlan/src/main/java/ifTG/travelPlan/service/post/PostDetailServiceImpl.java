package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestCommentAndImgsDto;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.post.PostDetailsWithImagesAndCommentsDto;
import ifTG.travelPlan.service.comment.CommentService;
import ifTG.travelPlan.service.filestore.PostImgFileService;
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
    private final PostImgFileService postImgFileService;
    private final CommentService commentService;
    private final PostViewService postViewService;

    @Override
    public PostDetailsWithImagesAndCommentsDto getPostDetail(RequestCommentAndImgsDto dto) {
        postViewService.addPostViewByPostIdAndUserId(dto.getPostId(), dto.getUserId());
        List<ImageToString> imageList = postImgFileService.getPostImageList(dto.getPostId(), dto.getImgUriList());
        List<CommentDtoWithUserInfo> commentDtoWithUserInfoList = commentService.getCommentListByPost(dto.getRequestCommentByPostDto());

        return new PostDetailsWithImagesAndCommentsDto(commentDtoWithUserInfoList, imageList);
    }
}
