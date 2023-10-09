package ifTG.travelPlan.service.api.dto.tourapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourApiResponseCountDto {
    private int numofrows;
    private int pageno;
    private int totalcount;
}
