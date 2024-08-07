package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.controller.dto.ResponseSearchEDestinationDto;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DestinationSearchService {
    ResponseSearchEDestinationDto findAllByKeyword(Long userId, String keyword, Pageable pageable);

}
