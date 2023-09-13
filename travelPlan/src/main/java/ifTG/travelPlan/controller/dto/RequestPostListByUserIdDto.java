package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class RequestPostListByUserIdDto {
    private final Pageable pageable;
    private final Long userId;

    public RequestPostListByUserIdDto(int page, int perPage, Long userId){
        this.userId = userId;
        this.pageable = PageRequest.of(page, perPage);
    }
}
