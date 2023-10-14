package ifTG.travelPlan.service.api.dto.tourapi.detailintro.leisuresports;

import ifTG.travelPlan.service.api.dto.tourapi.detailintro.DetailIntroDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class LeisureSportsDetailIntroDto implements DetailIntroDto {
    private List<LeisureSportsDetailIntroItem> item = new ArrayList<>();
}
