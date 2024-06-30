package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.dto.post.ToggleDto;

public interface DestinationLikeService {
    ToggleDto toggleLike(Long destinationId, Long userId);
}
