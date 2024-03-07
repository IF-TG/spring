package ifTG.travelPlan.converter;

import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.post.comment.NestedComment;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentDtoConverter {
    CommentDtoWithUserInfo getCommentDto(User user, Comment comment, boolean isLiked);

    List<CommentDtoWithUserInfo> getCommentDtoList(Page<Comment> commentList, List<Long> likedCommentIdList,
                                                   List<Long> likedNestedCommentIdList, List<Long> blockedUserIdList);

    List<NestedCommentDto> getNestedCommentDtoList(Comment comment, List<Long> likedNestedCommentIdListByUser);

    NestedCommentDto getNestedCommentDto(NestedComment nestedComment, boolean isLiked);
}
