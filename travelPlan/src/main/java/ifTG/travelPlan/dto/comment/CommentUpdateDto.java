package ifTG.travelPlan.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateDto {
    private final Long commentId;
    private final String comment;
}
