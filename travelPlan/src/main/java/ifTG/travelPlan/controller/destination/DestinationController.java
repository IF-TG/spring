package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.dto.destination.DestinationDetailDto;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;

import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.service.api.dto.ContentType;
import ifTG.travelPlan.service.destination.DestinationSearchService;
import ifTG.travelPlan.service.destination.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination")
@RequiredArgsConstructor
@Slf4j
public class DestinationController {
    private final DestinationService destinationService;
    private final DestinationSearchService destinationSearchService;

    @GetMapping("/detail")
    public ResponseEntity<Result<DestinationDetailDto>> findDestination(
            @RequestParam Long destinationId,
            @RequestParam Integer contentTypeId,
            @RequestParam Long userId){
        ContentType inputContent = ContentType.of(contentTypeId);
        if (inputContent == null){
            throw new CustomErrorException(StatusCode.INVALID_CONTENT_TYPE_ID);
        }
        return Result.isSuccess(destinationService.findByDestinationId(destinationId, inputContent, userId));
    }
    @GetMapping("/search")
    public ResponseEntity<Result<List<ResponseEDestinationDto>>> findAllByKeyword(
            @RequestParam Long userId,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage
    ){
        return Result.isSuccess(destinationSearchService.findAllByKeyword(userId, keyword, PageRequest.of(page,perPage)));
    }
}
