package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EDestinationConvertDtoServiceImpl implements EDestinationConvertDtoService {

    @Override
    public List<ResponseEDestinationDto> getResponseEDestinationDtoList(boolean isGptRelated, List<EDestination> eDestinationList, List<Long> destinationScrapIdList) {
        return eDestinationList.stream().map(ed -> ResponseEDestinationDto.builder()
                                                                          .id(ed.getId())
                                                                          .title(ed.getTitle())
                                                                          .thumbnailUrl(ed.getThumbnailUrl())
                                                                          .address(ed.getAddress())
                                                                          .largeCategory(ed.getCategory().getLargeCategory().getValue())
                                                                          .middleCategory(ed.getCategory().getMiddleCategory().getValue())
                                                                          .smallCategory(ed.getCategory().getSmallCategory().getValue())
                                                                          .contentType(ed.getContentType())
                                                                          .isScraped(destinationScrapIdList.contains(ed.getId()))
                                                                          .isGptRelated(isGptRelated)
                                                                          .build()).toList();
    }
}
