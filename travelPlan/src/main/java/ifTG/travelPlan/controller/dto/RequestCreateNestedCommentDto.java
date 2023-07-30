package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateNestedCommentDto {
    private final Long userId;
    private final Long commentId;
    private final String comment;
}
