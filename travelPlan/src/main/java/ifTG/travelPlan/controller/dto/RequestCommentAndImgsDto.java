package ifTG.travelPlan.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestCommentAndImgsDto {
    private final RequestCommentByPostDto requestCommentByPostDto;

    public RequestCommentAndImgsDto(Long postId, Long userId, int perPage) {
        this.requestCommentByPostDto = new RequestCommentByPostDto(0, perPage, postId, userId);
    }

    public Long getUserId(){
        return this.requestCommentByPostDto.getUserId();
    }

    public Long getPostId(){
        return this.requestCommentByPostDto.getPostId();
    }
}
