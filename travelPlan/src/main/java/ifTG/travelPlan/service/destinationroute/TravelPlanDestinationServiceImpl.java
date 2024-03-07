package ifTG.travelPlan.service.destinationroute;

import ifTG.travelPlan.controller.dto.DestinationRouteListWithTravelPlanIdDto;
import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.controller.dto.TravelPlanDestinationIdDto;
import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.domain.travel.TravelPlanDestination;
import ifTG.travelPlan.dto.travel.DataType;
import ifTG.travelPlan.dto.travel.TravelPlanDestinationDto;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.jdbc.JdbcDestinationRouteRepository;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanDestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanRepository;
import ifTG.travelPlan.service.destination.DestinationConvertDto;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelPlanDestinationServiceImpl implements TravelPlanDestinationService {
    private final TravelPlanDestinationRepository travelPlanDestinationRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final JdbcDestinationRouteRepository jdbcDestinationRouteRepository;
    private final EntityManager entityManager;
    private final DestinationConvertDto destinationConvertDto;
    @Override
    public List<TravelPlanDestinationDto> getDestinationRouteByTravelPlanId(Long userId, Long travelPlanId) {
        TravelPlan travelPlan = travelPlanRepository.findById(travelPlanId).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(!travelPlan.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        List<TravelPlanDestination> travelPlanDestinationList =
                travelPlanDestinationRepository.findWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId);
        return travelPlanDestinationList.stream().map(this::getDestinationRouteDtoByDestinationDto).toList();
    }

    @Override
    @Transactional
    public List<TravelPlanDestinationDto> addDestinationToTravelPlan(Long userId, DestinationRouteListWithTravelPlanIdDto dto) {
        List<TravelPlanDestination> travelPlanDestinationList = dto.getTravelPlan().stream().filter(d->d.getData().containsKey(DataType.DESTINATION))
                                                                   .map(d->TravelPlanDestination.builder()
                .travelPlanId(dto.getTravelPlanId())
                .destinationId(d.getData().get(DataType.DESTINATION))
                .eta(d.getEta())
                .build()).toList();
        TravelPlan travelPlan = travelPlanRepository.findById(dto.getTravelPlanId()).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(travelPlan.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        //travelPlanDestinationRepository.saveAllAndFlush(travelPlanDestinationList);
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
    @Transactional
    public List<TravelPlanDestinationDto> updateDestinationToTravelPlan(Long userId, DestinationRouteListWithTravelPlanIdDto dto) {
        TravelPlan travelPlan = travelPlanRepository.findById(dto.getTravelPlanId()).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(travelPlan.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        List<TravelPlanDestination> travelPlanDestinationList = travelPlanDestinationRepository.findAllByTravelPlanId(dto.getTravelPlanId());
        List<TravelPlanDestination> deleteTravelPlanDestinationList = new ArrayList<>();
        Map<Long, LocalDateTime> destinationIdAndEtaMap = getDestinationIdAndEtaMap(dto.getTravelPlan());
        travelPlanDestinationList.removeIf(travelPlanDestination -> {
            if (!destinationIdAndEtaMap.containsKey(travelPlanDestination.getId().getDestinationId())) {
                deleteTravelPlanDestinationList.add(travelPlanDestination);
                return true;
            }else return false;
        });
        travelPlanDestinationRepository.deleteAllInBatch(deleteTravelPlanDestinationList);

        travelPlanDestinationList.forEach(
                tpd->{
                    LocalDateTime updateEta = destinationIdAndEtaMap.get(tpd.getId().getDestinationId());
                    if(tpd.getEta().equals(updateEta)){
                        destinationIdAndEtaMap.remove(tpd.getId().getDestinationId());
                    }else{
                        tpd.updateTravelPlanDestination(updateEta);
                        destinationIdAndEtaMap.remove(tpd.getId().getDestinationId());
                    }
                }
        );
        List<TravelPlanDestination> insertTravelPlanDestinationList = getTravelPlanDestinationListByMap(dto.getTravelPlanId(), destinationIdAndEtaMap);
        jdbcDestinationRouteRepository.insertTravelPlanDestination(insertTravelPlanDestinationList);
        return getDestinationRouteDtoListByTravelPlanId(dto.getTravelPlanId());
    }

    private List<TravelPlanDestinationDto> getDestinationRouteDtoListByTravelPlanId(Long travelPlanId) {
        List<TravelPlanDestination> travelPlanDestinationList = getWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId);
        return travelPlanDestinationList
                .stream().map(this::getDestinationRouteDtoByDestinationDto).toList();
    }

    private List<TravelPlanDestination> getWithTravelPlanAndDestinationRouteByTravelPlanId(Long travelPlanId) {
        List<TravelPlanDestination> withTravelPlanAndDestinationRouteByTravelPlanId = travelPlanDestinationRepository.findWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId);

        return travelPlanDestinationRepository.findWithTravelPlanAndDestinationRouteByTravelPlanId(travelPlanId);
    }
    private TravelPlanDestinationDto getDestinationRouteDtoByDestinationDto(TravelPlanDestination travelPlanDestination) {
        TravelPlanDestinationDto dto = new TravelPlanDestinationDto();
        dto.addDestinationDto(
                destinationConvertDto.getDestinationDto(travelPlanDestination.getDestination(), false),
                travelPlanDestination.getEta()
        );
        return dto;
    }

    private Map<Long, LocalDateTime> getDestinationIdAndEtaMap(List<TravelPlanDestinationIdDto> travelPlanDestinationIdoDtoList) {
        return travelPlanDestinationIdoDtoList.stream().filter(d->d.getData().containsKey(DataType.DESTINATION))
                                           .collect(Collectors.toMap(d-> d.getData().get(DataType.DESTINATION), TravelPlanDestinationIdDto::getEta));
    }

    private List<TravelPlanDestination> getTravelPlanDestinationListByMap(Long travelPlanId, Map<Long, LocalDateTime> destinationIdAndEtaMap) {
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
