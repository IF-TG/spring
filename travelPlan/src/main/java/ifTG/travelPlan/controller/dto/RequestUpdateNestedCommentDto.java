package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestUpdateNestedCommentDto {
    private Long nestedCommentId;
    private String comment;
}
