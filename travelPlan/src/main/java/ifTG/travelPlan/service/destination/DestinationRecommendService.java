package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.dto.destination.ResponseERecommendDestinationDto;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface DestinationRecommendService {
    List<ResponseERecommendDestinationDto> getAllDestinationRecommend(Long userId, Pageable pageable);
}
