package ifTG.travelPlan.service.api.dto.tourapi.detailintro.culturalfacility;

import ifTG.travelPlan.service.api.dto.tourapi.detailintro.DetailIntroDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CulturalFacilityDetailIntroDto implements DetailIntroDto {
    private List<CulturalFacilityDetailIntroItem> item = new ArrayList<>();
}
