package ifTG.travelPlan.service.api.dto.tourapi.categorycode;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class CategoryCodeItem {
    private String code;
    private String name;
    private int mum;
}
