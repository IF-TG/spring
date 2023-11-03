package ifTG.travelPlan.elasticsearch.domain;

<<<<<<< HEAD
import ifTG.travelPlan.dto.travel.enums.Category;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;

@Document(indexName = "e_destination")
@Getter
@Mapping(mappingPath = "elastic/destination-mapping.json")
@Setting(settingPath = "elastic/destination-setting.json")
@AllArgsConstructor
@Builder
public class EDestination {
    @Id
    private Long id;
    private String title;
    private List<String> keywordList;
    private String thumbnailUrl;
    private String info;
<<<<<<< HEAD
    private String blindInfo;
    private String address;
    private Category category;
=======
    private Long likeNum;
    private String blindInfo;
    private String address;
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
}
