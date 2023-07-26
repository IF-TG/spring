package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.controller.dto.*;
import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelPlanServiceImpl implements TravelPlanService{
    private final TravelPlanRepository travelPlanRepository;
    private final UserRepository userRepository;
    @Override
    public TravelPlanDto saveTravelPlan(RequestTravelPlanDto requestTravelPlanDto) {
        User user = userRepository.findById(requestTravelPlanDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        TravelPlan travelPlan = TravelPlan.builder()
                        .title(requestTravelPlanDto.getTitle())
                        .user(user).build();

        travelPlan = travelPlanRepository.save(travelPlan);

        return new TravelPlanDto(travelPlan.getTravelPlanId(), travelPlan.getTitle());
    }

    @Override
    public TravelPlanDto updateTravelPlan(RequestTravelPlanDto requestTravelPlanDto) {
        TravelPlan travelPlan = travelPlanRepository.findById(requestTravelPlanDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        travelPlan.updateTravelPlan(requestTravelPlanDto.getTitle());

        return new TravelPlanDto(travelPlan.getTravelPlanId(), travelPlan.getTitle());
    }

    @Override
    public Boolean deleteTravelPlan(TravelPlanIdDto travelPlanIdDto) {
        travelPlanRepository.deleteById(travelPlanIdDto.getTravelPlanId());
        return true;
    }

    @Override
    public List<TravelPlanDto> getTravelPlanByUserId(RequestTravelPlanListDto requestTravelPlanListDto) {
        List<TravelPlan> travelPlanList = travelPlanRepository.findAllByUserId(requestTravelPlanListDto.getUserId());

        return travelPlanList.stream().map(
                travelPlan ->
                        new TravelPlanDto(travelPlan.getTravelPlanId(), travelPlan.getTitle())
        ).toList();
    }


}
