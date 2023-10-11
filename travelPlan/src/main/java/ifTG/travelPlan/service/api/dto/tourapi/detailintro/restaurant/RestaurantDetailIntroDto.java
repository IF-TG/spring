package ifTG.travelPlan.service.api.dto.tourapi.detailintro.restaurant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class RestaurantDetailIntroDto {
    private List<RestaurantDetailIntroItem> item = new ArrayList<>();
}
