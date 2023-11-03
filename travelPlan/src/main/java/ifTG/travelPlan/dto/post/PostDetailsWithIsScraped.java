package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostDetailsWithIsScraped {
    private List<CommentDtoWithUserInfo> commentList;
    private boolean isScraped;
}
