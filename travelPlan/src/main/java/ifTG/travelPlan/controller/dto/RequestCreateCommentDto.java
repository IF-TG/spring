package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateCommentDto {
    private final Long postId;
    private final Long userId;
    private final String comment;
}
