package ifTG.travelPlan.dto.user;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.post.ImgFile;
import ifTG.travelPlan.dto.travel.DestinationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ScrapFolderDto {
    private String title;
    private List<ImageToString> thumbnailList;
}
