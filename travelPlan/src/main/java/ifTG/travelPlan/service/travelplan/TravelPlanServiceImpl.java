package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.controller.dto.RequestTravelPlanDto;
import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.controller.dto.TravelPlanDto;
import ifTG.travelPlan.domain.travel.TravelPlan;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.travel.TravelPlanRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelPlanServiceImpl implements TravelPlanService{
    private final TravelPlanRepository travelPlanRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public TravelPlanDto saveTravelPlan(Long userId, RequestTravelPlanDto requestTravelPlanDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        TravelPlan travelPlan = TravelPlan.builder()
                        .title(requestTravelPlanDto.getTitle())
                        .user(user).build();

        travelPlan = travelPlanRepository.save(travelPlan);
        return new TravelPlanDto(travelPlan.getId(), travelPlan.getTitle());
    }

    @Override
    @Transactional
    public TravelPlanDto updateTravelPlan(Long userId, Long travelPlanId, RequestTravelPlanDto requestTravelPlanDto) {
        TravelPlan travelPlan = travelPlanRepository.findById(travelPlanId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(!travelPlan.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        travelPlan.updateTravelPlan(requestTravelPlanDto.getTitle());
        return new TravelPlanDto(travelPlan.getId(), travelPlan.getTitle());
    }

    @Override
    @Transactional
    public Boolean deleteTravelPlan(Long userId, Long travelPlanId) {
        TravelPlan travelPlan = travelPlanRepository.findById(travelPlanId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(!travelPlan.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        travelPlanRepository.delete(travelPlan);
        return true;
    }

    @Override
    public List<TravelPlanDto> getTravelPlanByUserId(Long userId) {
        List<TravelPlan> travelPlanList = travelPlanRepository.findAllByUserId(userId);

        return travelPlanList.stream().map(
                travelPlan ->
                        new TravelPlanDto(travelPlan.getId(), travelPlan.getTitle())
        ).toList();
    }


}
