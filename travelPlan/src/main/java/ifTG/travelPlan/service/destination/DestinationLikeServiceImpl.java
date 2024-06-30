package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.DestinationLike;
import ifTG.travelPlan.domain.travel.DestinationLikeId;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.travel.DestinationLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinationLikeServiceImpl implements DestinationLikeService{
    private final DestinationLikeRepository destinationLikeRepository;

    @Override
    @Transactional
    public ToggleDto toggleLike(Long destinationId, Long userId) {
        DestinationLikeId destinationLikeId = new DestinationLikeId(destinationId, userId);
        Optional<DestinationLike> destinationLike = destinationLikeRepository.findById(destinationLikeId);
        if (destinationLike.isEmpty()){
            destinationLikeRepository.save(new DestinationLike(destinationLikeId));
            return new ToggleDto(destinationId, true);
        }else{
            destinationLikeRepository.delete(destinationLike.get());
            return new ToggleDto(destinationId, false);
        }
    }
}
