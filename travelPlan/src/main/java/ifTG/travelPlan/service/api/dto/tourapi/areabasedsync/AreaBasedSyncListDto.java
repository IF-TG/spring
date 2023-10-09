package ifTG.travelPlan.service.api.dto.tourapi.areabasedsync;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AreaBasedSyncListDto {
    private List<AreaBasedSyncItem> item = new ArrayList<>();
}
