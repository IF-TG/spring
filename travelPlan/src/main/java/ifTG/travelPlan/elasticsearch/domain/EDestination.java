package ifTG.travelPlan.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "e_destination")
@Getter
@Mapping(mappingPath = "elastic/destination-mapping.json")
@Setting(settingPath = "elastic/destination-setting.json")
@AllArgsConstructor
@Builder
public class EDestination {
    @Id
    private String id;

    private String title;

    private List<String> keywordList;

    private String thumbnailUrl;

    private String info;

    private String blindInfo;
}
