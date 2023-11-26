package ifTG.travelPlan.elasticsearch.domain;


import ifTG.travelPlan.dto.travel.enums.Category;

import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

@Document(indexName = "e_destination")
@Getter
@Mapping(mappingPath = "elastic/destination-mapping.json")
@Setting(settingPath = "elastic/destination-setting.json")
@AllArgsConstructor
@ToString
@Builder
public class EDestination {
    @Id
    private Long id;
    private String title;
    private List<String> keywordList;
    private String thumbnailUrl;
    private String info;
    private String blindInfo;
    private String address;
    private Category category;
    private ContentType contentType;

    @Field(type = FieldType.Dense_Vector, dims = 100)
    private double[] embedding;
}
