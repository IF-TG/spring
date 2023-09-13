package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class RequestCommentAndImgsDto {
    private final RequestCommentByPostDto requestCommentByPostDto;
    private final List<String> imgUriList;

    public RequestCommentAndImgsDto(Long postId, Long userId, int perPage, List<String> imgUriList) {
        this.requestCommentByPostDto = new RequestCommentByPostDto(0, perPage, postId, userId);
        this.imgUriList = imgUriList;
    }

    public Long getUserId(){
        return this.requestCommentByPostDto.getUserId();
    }

    public Long getPostId(){
        return this.requestCommentByPostDto.getPostId();
    }
}
