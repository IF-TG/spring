package ifTG.travelPlan.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ImageToString {
    private String img;
    private ImageType imageType;
}
