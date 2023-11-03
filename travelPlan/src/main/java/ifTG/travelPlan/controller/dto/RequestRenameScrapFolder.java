package ifTG.travelPlan.controller.dto;

import lombok.Getter;

@Getter
public class RequestRenameScrapFolder {
    private Long userId;
    private String oldFolderName;
    private String newFolderName;
}
