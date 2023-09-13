package ifTG.travelPlan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ImageToString {
    private final String img;
    private final ImageType imageType;
}
