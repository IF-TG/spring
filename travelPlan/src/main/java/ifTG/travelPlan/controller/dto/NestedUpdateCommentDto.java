package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NestedUpdateCommentDto {
    private final Long nestedCommentId;
    private final String comment;
}
