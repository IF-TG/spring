package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Slf4j
public class RequestCommentByPostDto {
    private final Pageable pageable;
    private final Long postId;
    private final Long userId;

    public RequestCommentByPostDto(int page, int perPage, Long postId, Long userId){
        log.info("commentdto = {},{},{},{}", page, perPage, postId, userId);
        this.pageable = PageRequest.of(page, perPage);
        this.postId = postId;
        this.userId = userId;
    }
}
