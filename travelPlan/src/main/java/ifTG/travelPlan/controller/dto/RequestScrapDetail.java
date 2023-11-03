package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class RequestScrapDetail {
    private String folderName;
    private Long userId;
    private Pageable pageable;

    public RequestScrapDetail(String folderName, Long userId, Integer page, Integer perPage) {
        this.folderName = folderName;
        this.userId = userId;
        this.pageable = PageRequest.of(page, perPage);
    }
}
