package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.user.UserVector;
import ifTG.travelPlan.dto.destination.ResponseERecommendDestinationDto;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRecommendRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.UserVectorRepository;
import ifTG.travelPlan.service.user.UserVectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationRecommendServiceImpl implements DestinationRecommendService{
    private final UserVectorRepository userVectorRepository;
    private final EDestinationRecommendRepository eDestinationRecommendRepository;
    private final EDestinationConvertDtoService eDestinationConvertDtoService;
    private final DestinationScrapRepository destinationScrapRepository;
    private final UserVectorService userVectorService;
    @Value("${nlp.dimension}")
    private Integer dimension;

    @Override
    @Transactional
    public List<ResponseERecommendDestinationDto> getAllDestinationRecommend(Long userId, Pageable pageable) {
        List<Long> allDestinationIdByUserId = destinationScrapRepository.findAllDestinationIdByUserId(userId);
        UserVector userVector = userVectorRepository.findByUserId(userId).orElseGet(()->userVectorService.initUserVector(userId));
        double[] vector = new double[dimension];
        for (int i = 0; i<dimension; i++){
            double value;
            if (!userVector.getVector().containsKey(i))value = 1;
            else value = userVector.getVector().get(i);
            vector[i] = value;
        }
        return allResponseERecommendDestinationDto(pageable, allDestinationIdByUserId,  vector);
    }

    private List<ResponseERecommendDestinationDto> allResponseERecommendDestinationDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<ResponseERecommendDestinationDto> result = new ArrayList<>();
        result.add(new ResponseERecommendDestinationDto("관광지 어떠세요?", getSightseeingDto(pageable, allDestinationIdByUserId, userVector)));
        result.add(new ResponseERecommendDestinationDto("문화 시설 어떠세요?", getCulturalDto(pageable, allDestinationIdByUserId, userVector)));
        result.add(new ResponseERecommendDestinationDto("축제 어떠세요?", getEventDto(pageable, allDestinationIdByUserId, userVector)));
        result.add(new ResponseERecommendDestinationDto("쇼핑 어떠세요?", getShoppingDto(pageable, allDestinationIdByUserId, userVector)));
        result.add(new ResponseERecommendDestinationDto("레저츠,야영 어떠세요?", getLeisureDto(pageable, allDestinationIdByUserId, userVector)));
        result.add(new ResponseERecommendDestinationDto("맛집 어떠세요?", getRestaurantDto(pageable, allDestinationIdByUserId, userVector)));
        return result;
    }

    private List<ResponseEDestinationDto> getShoppingDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<EDestination> shopping = eDestinationRecommendRepository.findShoppingByUserVector(userVector, pageable);
        return eDestinationConvertDtoService.getResponseEDestinationDtoList(false, shopping, allDestinationIdByUserId);
    }
    private List<ResponseEDestinationDto> getLeisureDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<EDestination> leisure = eDestinationRecommendRepository.findLeisureByUserVector(userVector, pageable);
        return eDestinationConvertDtoService.getResponseEDestinationDtoList(false, leisure, allDestinationIdByUserId);
    }
    private List<ResponseEDestinationDto> getRestaurantDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<EDestination> restaurant = eDestinationRecommendRepository.findRestaurantByUserVector(userVector, pageable);
        return eDestinationConvertDtoService.getResponseEDestinationDtoList(false, restaurant, allDestinationIdByUserId);
    }
    private List<ResponseEDestinationDto> getEventDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<EDestination> event = eDestinationRecommendRepository.findEventByUserVector(userVector, pageable);
        return eDestinationConvertDtoService.getResponseEDestinationDtoList(false, event, allDestinationIdByUserId);
    }

    private List<ResponseEDestinationDto> getCulturalDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<EDestination> cultural = eDestinationRecommendRepository.findCulturalByUserVector(userVector, pageable);
        return eDestinationConvertDtoService.getResponseEDestinationDtoList(false, cultural, allDestinationIdByUserId);
    }

    private List<ResponseEDestinationDto> getSightseeingDto(Pageable pageable, List<Long> allDestinationIdByUserId, double[] userVector) {
        List<EDestination> sightseeing = eDestinationRecommendRepository.findSightSeeingByUserVector(userVector, pageable);
        return eDestinationConvertDtoService.getResponseEDestinationDtoList(false, sightseeing, allDestinationIdByUserId);
    }
}
