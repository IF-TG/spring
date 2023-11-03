package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
<<<<<<< HEAD
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de

import java.util.List;

public interface DestinationService {
<<<<<<< HEAD
    List<ResponseEDestinationDto> findAllByKeyword(RequestSearchDestinationDto dto);
=======
    List<EDestination> findAllByKeyword(RequestSearchDestinationDto dto);
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
}
