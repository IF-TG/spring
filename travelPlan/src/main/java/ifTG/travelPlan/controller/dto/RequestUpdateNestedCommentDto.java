package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestUpdateNestedCommentDto {

    private final Long commentId;
    private final String comment;

    private Long nestedCommentId;

}
