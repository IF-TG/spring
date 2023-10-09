package ifTG.travelPlan.service.api.dto.tourapi.detailcommon;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
public class DetailCommonDto {
    private DetailCommonItem item;
}
