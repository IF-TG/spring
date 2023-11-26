package ifTG.travelPlan.dto.travel;


import ifTG.travelPlan.dto.travel.enums.Category;
import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DestinationDto {
    private Long id;
    private int contentTypeId;
    private String title;
    private String address;
    private String addressDetail;
    private Double mapX;
    private Double mapY;
    private String overview;
    private String tel;
    private CategoryDto category;
    private String zipcode;
    private String thumbnail;
    private boolean isScraped;

    @Builder
    public DestinationDto(Long id, ContentType contentType, String title, String address, String addressDetail, Double mapX, Double mapY, String overview, String tel, Category category, String zipcode, String thumbnail, boolean isScraped) {
        this.id = id;
        this.contentTypeId = contentType.getValue();
        this.title = title;
        this.address = address;
        this.addressDetail = addressDetail;
        this.mapX = mapX;
        this.mapY = mapY;
        this.overview = overview;
        this.tel = tel;
        this.category = new CategoryDto(category.getLargeCategory().getValue(), category.getMiddleCategory().getValue(), category.getSmallCategory().getValue());
        this.zipcode = zipcode;
        this.thumbnail = thumbnail;
        this.isScraped = isScraped;
    }
    public void setScraped(boolean scraped) {
        isScraped = scraped;
    }
}
