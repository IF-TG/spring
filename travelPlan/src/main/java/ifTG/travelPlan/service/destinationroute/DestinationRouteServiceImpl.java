package ifTG.travelPlan.service.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.controller.dto.RequestSaveDestinationToTravelPlanDto;
import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.dto.travel.DataType;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.dto.travel.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;
import ifTG.travelPlan.domain.travel.TravelPlanDestination;
import ifTG.travelPlan.repository.jdbc.JdbcDestinationRouteRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanDestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationRouteServiceImpl implements DestinationRouteService{
    private final TravelPlanDestinationRepository travelPlanDestinationRepository;
    private final DestinationRepository destinationRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final JdbcDestinationRouteRepository jdbcDestinationRouteRepository;

    @Override
    public List<DestinationRouteDto> getDestinationRouteByTravelPlanId(TravelPlanIdDto travelPlanId) {
        List<TravelPlanDestination> travelPlanDestinationList =
                travelPlanDestinationRepository.findWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId.getTravelPlanId());
        return travelPlanDestinationList.stream().map(DestinationRouteServiceImpl::getDestinationRouteDtoByDestinationDto).toList();
    }

    @Override
    public DestinationDto addDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto) {
        TravelPlan travelPlan = travelPlanRepository.findById(dto.getTravelPlanId()).orElseThrow(EntityNotFoundException::new);

        Map<Long, LocalDateTime> destinationIdList = dto.getData().stream().filter(d->d.getDestination()!=null)
                .collect(Collectors.toMap(d-> d.getDestination().getId(), DestinationRouteDto::getEta));
        List<Destination> destinationList = destinationRepository.findAllById(destinationIdList.keySet());

        List<TravelPlanDestination> travelPlanDestinationList = destinationList.stream()
                .map(
                        d-> TravelPlanDestination.builder()
                                .destination(d)
                                .travelPlan(travelPlan)
                                .eta()
                )
        return getDestinationDto(destination);
    }

    private static DestinationRouteDto getDestinationRouteDtoByDestinationDto(TravelPlanDestination travelPlanDestination) {
        DestinationRouteDto dto = new DestinationRouteDto();
        dto.addDestinationDto(
                getDestinationDto(travelPlanDestination.getDestination()),
                travelPlanDestination.getEta()
        );
        return dto;
    }

    private static DestinationDto getDestinationDto(Destination destination) {
        return DestinationDto.builder()
                .id(destination.getId())
                .name(destination.getDestinationName())
                .likes(destination.getLikes())
                .address(destination.getLocation())
                .build();
    }
}
