package ifTG.travelPlan.elasticsearch.dto;

<<<<<<< HEAD
import ifTG.travelPlan.service.api.dto.CatDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseEDestinationDto {
    private Long id;
    private String title;
    private String thumbnailUrl;
    private String address;
    private String LargeCategory;
    private String MiddleCategory;
    private String SmallCategory;
    private boolean isScraped;
    private boolean isGptRelated;

=======
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseEDestinationDto {
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
}
