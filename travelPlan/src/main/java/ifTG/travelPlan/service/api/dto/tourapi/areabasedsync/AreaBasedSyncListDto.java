package ifTG.travelPlan.service.api.dto.tourapi.areabasedsync;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AreaBasedSyncListDto {
    private List<AreaBasedSyncItem> item = new ArrayList<>();
}
