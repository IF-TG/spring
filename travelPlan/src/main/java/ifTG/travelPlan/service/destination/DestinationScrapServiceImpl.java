package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestUpdateDestinationScrapDto;
import ifTG.travelPlan.controller.dto.RequestScrapDetail;
import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.travel.DestinationScrapId;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DestinationScrapServiceImpl implements DestinationScrapService{
    private final DestinationScrapRepository destinationScrapRepository;
    private final DestinationCovertDto destinationCovertDto;

    @Override
    public ToggleDto toggleDestinationScrap(RequestScrapDto dto) {
        DestinationScrapId destinationScrapId = new DestinationScrapId(dto.getObjectId(), dto.getUserId());
        Optional<DestinationScrap> destinationScrap = destinationScrapRepository.findById(destinationScrapId);
        DestinationScrap newDestinationScrap = destinationScrap.orElseGet(()->new DestinationScrap(destinationScrapId, dto.getFolderName()));
        if (destinationScrap.isEmpty()){
            destinationScrapRepository.save(newDestinationScrap);
            return new ToggleDto(destinationScrapId.getDestinationId(), true);
        }else{
            destinationScrapRepository.delete(newDestinationScrap);
            return new ToggleDto(destinationScrapId.getDestinationId(), false);
        }
    }

    @Override
    public List<ScrapDto> updateDestinationScrap(RequestUpdateDestinationScrapDto dto) {
        List<DestinationScrapId> destinationScrapIdList = dto.getObjectIdList().stream().map(d->new DestinationScrapId(d, dto.getUserId())).toList();
        List<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllById(destinationScrapIdList);
        destinationScrapList.forEach(d->d.updateFolderName(dto.getFolderName()));
        destinationScrapList = destinationScrapRepository.saveAll(destinationScrapList);
        return destinationScrapList.stream().map(d-> new ScrapDto(
                d.getDestinationLikeId().getDestinationId(),
                d.getDestinationLikeId().getUserId(),
                d.getFolderName())).toList();
    }

    @Override
    public List<DestinationDto> findAllDestinationScrapsByScrapFolderAndUserId(RequestScrapDetail dto) {
        Slice<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllWithDestinationByFolderNameAndUserId(dto.getFolderName(), dto.getUserId(), dto.getPageable());
        return destinationCovertDto.getDestinationDtoListByScrap(destinationScrapList.stream().map(DestinationScrap::getDestination).toList());
    }

}
