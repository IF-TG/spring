package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.controller.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateDto {
    private final Long postId;
    private final PostCreateDto post;
}
