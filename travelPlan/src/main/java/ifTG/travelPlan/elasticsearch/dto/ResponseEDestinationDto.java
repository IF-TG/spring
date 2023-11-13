package ifTG.travelPlan.elasticsearch.dto;


import ifTG.travelPlan.service.api.dto.CatDto;
import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseEDestinationDto {
    private Long id;
    private int contentTypeId;
    private String title;
    private String thumbnailUrl;
    private String address;
    private String LargeCategory;
    private String MiddleCategory;
    private String SmallCategory;
    private boolean isScraped;
    private boolean isGptRelated;

    @Builder
    public ResponseEDestinationDto(Long id, ContentType contentType, String title, String thumbnailUrl, String address, String largeCategory, String middleCategory, String smallCategory, boolean isScraped, boolean isGptRelated) {
        this.id = id;
        this.contentTypeId = contentType.getValue();
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.address = address;
        LargeCategory = largeCategory;
        MiddleCategory = middleCategory;
        SmallCategory = smallCategory;
        this.isScraped = isScraped;
        this.isGptRelated = isGptRelated;
    }
}
