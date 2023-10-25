package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.travel.DestinationScrapId;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DestinationScrapServiceImpl implements DestinationScrapService{
    private final DestinationScrapRepository destinationScrapRepository;


    @Override
    public ToggleDto toggleDestinationScrap(RequestScrapDto dto) {
        DestinationScrapId destinationScrapId = new DestinationScrapId(dto.getObjectId(), dto.getUserId());
        Optional<DestinationScrap> destinationScrap = destinationScrapRepository.findById(destinationScrapId);
        if (destinationScrap.isEmpty()){
            DestinationScrap newDestinationScrap = new DestinationScrap(destinationScrapId, dto.getFolderName());
            destinationScrapRepository.save(newDestinationScrap);
            return new ToggleDto(destinationScrapId.getDestinationId(), true);
        }else{
            destinationScrapRepository.delete(destinationScrap.get());
            return new ToggleDto(destinationScrapId.getDestinationId(), false);
        }
    }

    @Override
    public ScrapDto updateFolderName(RequestScrapDto dto) {
        DestinationScrapId destinationScrapId = new DestinationScrapId(dto.getObjectId(), dto.getUserId());
        DestinationScrap destinationScrap = destinationScrapRepository.findById(destinationScrapId).orElseThrow(()->new NullPointerException("DestinationScrap 을 찾을 수 없습니다."));
        destinationScrapRepository.save(destinationScrap.updateFolderName(dto.getFolderName()));
        return new ScrapDto(
                destinationScrapId.getDestinationId(),
                destinationScrapId.getUserId(),
                destinationScrap.getFolderName()
        );
    }
}
