package ifTG.travelPlan.dto.user;

import ifTG.travelPlan.dto.ImageToString;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScrapFolderDto {
    private String title;
    private List<ImageToString> thumbnailList;
}
