package ifTG.travelPlan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestUpdateNestedCommentDto {
<<<<<<< HEAD
    private final Long commentId;
    private final String comment;
=======
    private Long nestedCommentId;
    private String comment;
>>>>>>> 7087be0c1c8494e11d31c83ed4964cca6ca052a6
}
