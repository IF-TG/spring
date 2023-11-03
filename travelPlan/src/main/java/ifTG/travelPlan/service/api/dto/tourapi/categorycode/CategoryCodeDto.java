
package ifTG.travelPlan.service.api.dto.tourapi.categorycode;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class CategoryCodeDto {
    private List<CategoryCodeItem> item = new ArrayList<>();

}
