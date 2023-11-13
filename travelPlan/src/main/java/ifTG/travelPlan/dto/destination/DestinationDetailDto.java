package ifTG.travelPlan.dto.destination;

import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class DestinationDetailDto {
    private DestinationDto destination;
    private final Map<ContentType, Object> detail = new HashMap<>();
    private boolean isLiked;

    public DestinationDetailDto(DestinationDto destination, Object object, boolean isLiked) {
        this.destination = destination;
        detail.put(ContentType.of(destination.getContentTypeId()), object);
        this.isLiked = isLiked;
    }
}
