package ifTG.travelPlan.service.api.dto.tourapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class AreaBasedSyncListDto {
    private List<AreaBasedSyncItemList> items;
    private int numofrows;
    private int pageno;
    private int totalcount;
}
