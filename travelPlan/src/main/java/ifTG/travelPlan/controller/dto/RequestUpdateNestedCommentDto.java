package ifTG.travelPlan.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestUpdateNestedCommentDto {
    private Long nestedCommentId;
    private String comment;
}
