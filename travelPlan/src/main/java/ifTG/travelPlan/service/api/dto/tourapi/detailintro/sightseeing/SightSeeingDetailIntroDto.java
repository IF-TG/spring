package ifTG.travelPlan.service.api.dto.tourapi.detailintro.sightseeing;

import ifTG.travelPlan.service.api.dto.tourapi.detailintro.DetailIntroDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class SightSeeingDetailIntroDto implements DetailIntroDto {
    private final List<SightSeeingDetailIntroItem> item = new ArrayList<>();
}
