package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class RequestAllUserLikeOrCommentPostDto {
    private Long userId;
    private Pageable pageable;

    public RequestAllUserLikeOrCommentPostDto(Long userId, int page, int perPage) {
        this.userId = userId;
        this.pageable = PageRequest.of(page,perPage);
    }
}
