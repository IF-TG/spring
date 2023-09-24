package ifTG.travelPlan.service.api.dto.tourapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class AreaBasedSyncItemList {
    private final List<AreaBasedSyncItem> item = new ArrayList<>();
}
