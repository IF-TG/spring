package ifTG.travelPlan.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NestedUpdateCommentDto {
    private final Long nestedCommentId;
    private final String comment;
}
