package ifTG.travelPlan.service.api.dto.tourapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourApiResponseDto<T> {
    private TourApiHeadBody<T> response;
}
