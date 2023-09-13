package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class RequestSearchHistoryPageDto {
    private final Pageable pageable;
    private final Long userId;

    public RequestSearchHistoryPageDto(int page, int perPage, Long userId) {
        this.pageable = PageRequest.of(page, perPage);
        this.userId = userId;
    }
}
