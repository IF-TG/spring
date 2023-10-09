package ifTG.travelPlan.service.api.dto.tourapi.detailintro.destination;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class SightSeeingDetailIntroDto {
    private List<SightSeeingDetailIntroItem> item = new ArrayList<>();
}
