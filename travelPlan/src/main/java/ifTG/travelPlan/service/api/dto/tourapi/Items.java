package ifTG.travelPlan.service.api.dto.tourapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Items<T> {
    private T items;
}
