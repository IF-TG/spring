package ifTG.travelPlan.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestBlockUserDto {

    @NotNull
    private final Long blockedUserId;
}
