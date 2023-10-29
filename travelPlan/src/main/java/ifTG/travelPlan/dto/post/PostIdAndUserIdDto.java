package ifTG.travelPlan.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostIdAndUserIdDto {
    private final Long postId;
    private final String userId;
}
