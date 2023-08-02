package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class RequestSearchPostDto {
    private final String keyword;
    private final boolean isContent;
    private final boolean isTitle;
    private final Long userId;
    private final Pageable pageable;


    public RequestSearchPostDto(String keyword, Long userId, boolean isTitle, boolean isContent, int page, int perPage) {
        this.keyword = keyword;
        this.userId = userId;
        this.isContent = isContent;
        this.isTitle = isTitle;
        this.pageable = PageRequest.of(page, perPage);
    }
}
