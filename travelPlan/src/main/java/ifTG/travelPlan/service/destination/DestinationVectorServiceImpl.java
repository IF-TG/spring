package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.domain.user.UserVector;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.user.UserVectorRepository;
import ifTG.travelPlan.service.user.UserVectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DestinationVectorServiceImpl implements DestinationVectorService{
    private final EDestinationRepository eDestinationRepository;
    private final UserVectorRepository userVectorRepository;
    private final UserVectorService userVectorService;
    @Async
    @Override
    public void updateUserVectorByDestination(Long userId, Long destinationId){
        EDestination eDestination = eDestinationRepository.findById(destinationId).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        UserVector userVector = userVectorRepository.findByUserId(userId).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_USER));
        userVectorService.updateUserVector(userVector, eDestination.getEmbedding());
    }
}
