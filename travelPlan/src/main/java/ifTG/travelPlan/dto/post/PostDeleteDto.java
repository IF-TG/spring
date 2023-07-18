package ifTG.travelPlan.dto.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostDeleteDto {
    private Long postId;

    public PostDeleteDto(Long postId){
        this.postId = postId;
    }
}
