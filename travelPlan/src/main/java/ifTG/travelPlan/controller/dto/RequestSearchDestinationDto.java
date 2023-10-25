package ifTG.travelPlan.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
public class RequestSearchDestinationDto {
    @NotNull
    private Long userId;
    @NotNull
    private String keyword;
    private Pageable pageable;

    public RequestSearchDestinationDto(Long userId, String keyword, int page, int perPage) {
        this.userId = userId;
        this.keyword = keyword;
        this.pageable = PageRequest.of(page, perPage);
    }
}
