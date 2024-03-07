package ifTG.travelPlan.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserScrapFolderDto {
    private String folderName;
    private int count;
    private List<String> thumbnailList;
}
