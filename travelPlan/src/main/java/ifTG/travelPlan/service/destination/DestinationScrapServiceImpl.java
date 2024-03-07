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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DestinationScrapServiceImpl implements DestinationScrapService{
    private final DestinationScrapRepository destinationScrapRepository;
    private final DestinationConvertDto destinationConvertDto;

    @Override
    @Transactional
    public ToggleDto toggleDestinationScrap(Long userId, RequestScrapDto dto) {
        DestinationScrapId destinationScrapId = new DestinationScrapId(dto.getObjectId(), userId);
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
    @Transactional
    public List<ScrapDto> updateDestinationScrap(Long userId, RequestUpdateDestinationScrapDto dto) {
        List<DestinationScrapId> destinationScrapIdList = dto.getObjectIdList().stream().map(d->new DestinationScrapId(d, userId)).toList();
        List<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllById(destinationScrapIdList);
        destinationScrapList.forEach(d->d.updateFolderName(dto.getFolderName()));
        destinationScrapList = destinationScrapRepository.saveAll(destinationScrapList);
        return destinationScrapList.stream().map(d-> new ScrapDto(
                d.getDestinationLikeId().getDestinationId(),
                d.getDestinationLikeId().getUserId(),
                d.getFolderName())).toList();
    }

    @Override
    public List<DestinationDto> findAllDestinationScrapsByScrapFolderAndUserId(String folderName, Long userId, Pageable pageable) {
        Slice<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllWithDestinationByFolderNameAndUserId(folderName, userId, pageable);
        return destinationConvertDto.getDestinationDtoListByScrap(destinationScrapList.stream().map(DestinationScrap::getDestination).toList());
    }

    @Override
    public Boolean deleteAllByFolderName(Long userId, String folderName) {
        destinationScrapRepository.deleteAllByFolderNameAndUserId(userId, folderName);
        return true;
    }

}
