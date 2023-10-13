package ifTG.travelPlan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostScrapDto {
    private Long postId;
    private Long userId;
    private String folderName;

}
