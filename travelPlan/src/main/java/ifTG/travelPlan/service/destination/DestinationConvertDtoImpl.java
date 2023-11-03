package ifTG.travelPlan.service.destination;


import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.dto.travel.DestinationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationConvertDtoImpl implements DestinationCovertDto{
    @Override
    public DestinationDto getDestinationDto(Destination destination, boolean isScraped){
        return DestinationDto.builder()
                              .id(destination.getId())
                              .title(destination.getTitle())
                              .address(destination.getAddress())
                              .addressDetail(destination.getAddressDetail())
                              .mapX(destination.getMapX())
                              .mapY(destination.getMapY())
                              .overview(destination.getOverview())
                              .tel(destination.getTel())
                              .category(destination.getCategory())
                              .zipcode(destination.getZipcode())
                              .thumbnail(destination.getThumbNail())
                              .isScraped(isScraped).build();
    }

    @Override
    public List<DestinationDto> getDestinationDtoListByScrap(List<Destination> destinationList){
        return destinationList.stream().map(d->getDestinationDto(d, true)).toList();
    }
}
