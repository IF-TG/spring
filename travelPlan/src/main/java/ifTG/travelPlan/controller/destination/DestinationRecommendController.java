package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.destination.ResponseERecommendDestinationDto;
import ifTG.travelPlan.service.destination.DestinationRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/destination/recommend")
public class DestinationRecommendController {
    private final DestinationRecommendService destinationRecommendService;

    @GetMapping
    public ResponseEntity<Result<List<ResponseERecommendDestinationDto>>> getDestinationRecommend(
            @AuthenticationUser Optional<Long> userId,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int perPage
    ) {
        return userId.map(aLong -> Result.isSuccess(destinationRecommendService.getAllDestinationRecommend(aLong, PageRequest.of(page, perPage)))).orElseGet(() -> Result.isSuccess(destinationRecommendService.getAllDestinationRecommend(PageRequest.of(page, perPage))));
    }

}
