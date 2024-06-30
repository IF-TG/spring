package ifTG.travelPlan.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestBlockUserDto {

    @NotNull
    private Long blockedUserId;
}
