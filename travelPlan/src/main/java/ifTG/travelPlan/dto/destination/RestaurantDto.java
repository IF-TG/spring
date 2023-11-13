package ifTG.travelPlan.dto.destination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RestaurantDto {
    private Long restaurantId;
    private String featuredMenu;
    private String treatMenu;
    private String openDate;
    private String openTime;
    private String packing;
    private String parking;
    private String restDate;
    private String seat;
    private String scale;
}
