package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class RequestCommentByPostDto {
    private final Pageable pageable;
    private final Long postId;
    private final Long userId;

    public RequestCommentByPostDto(int page, int perPage, Long postId, Long userId){
        this.pageable = PageRequest.of(page, perPage);
        this.postId = postId;
        this.userId = userId;
    }
}
