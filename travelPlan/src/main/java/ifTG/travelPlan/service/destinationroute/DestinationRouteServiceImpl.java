package ifTG.travelPlan.service.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.dto.travel.DestinationDto;
import ifTG.travelPlan.dto.travel.DestinationRouteDto;
import ifTG.travelPlan.controller.dto.TravelPlanIdDto;
import ifTG.travelPlan.domain.travel.TravelPlanDestination;
import ifTG.travelPlan.repository.jdbc.JdbcDestinationRouteRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanDestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
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
    public List<DestinationRouteDto> addDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto) {
        Map<Long, LocalDateTime> destinationIdAndEtaMap = getDestinationIdAndEtaMap(dto.getData());
        List<TravelPlanDestination> travelPlanDestinationList = getTravelPlanDestinationListByMap(dto.getTravelPlanId(), destinationIdAndEtaMap);
        jdbcDestinationRouteRepository.insertTravelPlanDestination(travelPlanDestinationList);

        return getDestinationRouteDtoListByTravelPlanId(dto.getTravelPlanId());
    }




    /**
     * 1. 변경할 데이터가 db에 해당 데이터가 없다면 삭제
     * 2. 변경할 데이터가 db에 있는데 eta가 다르면 갱신
     * 3. 변경 사항 없다면 유지
     * 4. 변경할 데이터 삽입
     */

    @Override
    public List<DestinationRouteDto> updateDestinationToTravelPlan(DestinationRouteListWithTravelPlanIdDto dto) {
        List<TravelPlanDestination> travelPlanDestinationList = travelPlanDestinationRepository.findAllByTravelPlanId(dto.getTravelPlanId());
        Map<Long, LocalDateTime> destinationIdAndEtaMap = getDestinationIdAndEtaMap(dto.getData());

        travelPlanDestinationList.removeIf(travelPlanDestination -> !destinationIdAndEtaMap.containsKey(travelPlanDestination.getId().getDestinationId()));
        travelPlanDestinationList.forEach(
                tpd->{
                    LocalDateTime updateEta = destinationIdAndEtaMap.get(tpd.getId().getDestinationId());
                    if(tpd.getEta().equals(updateEta)){
                        tpd.updateTravelPlanDestination(updateEta);
                        destinationIdAndEtaMap.remove(tpd.getId().getDestinationId());
                    }
                }
        );
        travelPlanDestinationRepository.saveAll(travelPlanDestinationList);

        List<TravelPlanDestination> insertTravelPlanDestinationList = getTravelPlanDestinationListByMap(dto.getTravelPlanId(), destinationIdAndEtaMap);
        jdbcDestinationRouteRepository.insertTravelPlanDestination(insertTravelPlanDestinationList);

        return getDestinationRouteDtoListByTravelPlanId(dto.getTravelPlanId());
    }

    private List<DestinationRouteDto> getDestinationRouteDtoListByTravelPlanId(Long travelPlanId) {
        return getWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId)
                .stream().map(DestinationRouteServiceImpl::getDestinationRouteDtoByDestinationDto).toList();
    }

    private List<TravelPlanDestination> getWithTravelPlanAndDestinationRouteByTravelPlanId(Long travelPlanId) {
        return travelPlanDestinationRepository.findWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId);
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

    private static Map<Long, LocalDateTime> getDestinationIdAndEtaMap(List<DestinationRouteDto> destinationRouteDtoList) {
        return destinationRouteDtoList.stream().filter(d->d.getDestination()!=null)
                                      .collect(Collectors.toMap(d-> d.getDestination().getId(), DestinationRouteDto::getEta));
    }

    private static List<TravelPlanDestination> getTravelPlanDestinationListByMap(Long travelPlanId, Map<Long, LocalDateTime> destinationIdAndEtaMap) {
        return destinationIdAndEtaMap.keySet().stream()
                                     .map(
                                             d -> TravelPlanDestination.builder()
                                                                       .travelPlanId(travelPlanId)
                                                                       .destinationId(d)
                                                                       .eta(destinationIdAndEtaMap.get(d))
                                                                       .build()
                                     ).toList();
    }
}
