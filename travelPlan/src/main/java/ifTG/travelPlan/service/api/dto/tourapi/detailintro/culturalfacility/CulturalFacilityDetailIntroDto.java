package ifTG.travelPlan.service.api.dto.tourapi.detailintro.culturalfacility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CulturalFacilityDetailIntroDto {
    private List<CulturalFacilityDetailIntroItem> item = new ArrayList<>();
}
