package ifTG.travelPlan.service.destination;


import org.springframework.scheduling.annotation.Async;

public interface DestinationVectorService {
    @Async
    void updateUserVectorByDestination(Long userId, Long destinationId);
}
