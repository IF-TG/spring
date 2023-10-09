package ifTG.travelPlan.service.api.dto.tourapi.detailcommon;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class DetailCommonDto {
    private List<DetailCommonItem> item = new ArrayList<>();
}
