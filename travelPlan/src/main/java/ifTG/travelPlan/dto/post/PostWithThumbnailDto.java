package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.dto.ImageToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostWithThumbnailDto {
    private List<ImageToString> thumbnail;
    private PostDto post;

}
