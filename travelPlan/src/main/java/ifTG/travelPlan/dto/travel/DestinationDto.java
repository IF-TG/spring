package ifTG.travelPlan.dto.travel;


import ifTG.travelPlan.dto.travel.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DestinationDto {
    private Long id;
    private String title;
    private String address;
    private String addressDetail;
    private Double mapX;
    private Double mapY;
    private String overview;
    private String tel;
    private Category category;
    private String zipcode;
    private String thumbnail;
    private boolean isScraped;

}
