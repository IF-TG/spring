package ifTG.travelPlan.dto.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostIdDto {
    private Long postId;

    public PostIdDto(Long postId){
        this.postId = postId;
    }
}
