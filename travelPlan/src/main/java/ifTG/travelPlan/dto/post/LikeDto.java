package ifTG.travelPlan.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LikeDto {
    private Long objectId;
    private Boolean like;
}
