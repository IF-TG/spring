package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.destination.DestinationDetailDto;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;

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
            Result<DestinationDetailDto> errorResult = new Result<DestinationDetailDto>()
                    .isError("InvalidContentTypeIdException");
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                new Result<>(
                        destinationService.findByDestinationId(destinationId, inputContent, userId)
                ),
                HttpStatus.OK
        );
    }
    @GetMapping("/search")
    public Result<List<ResponseEDestinationDto>> findAllByKeyword(
            @RequestParam Long userId,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage
    ){
        return new Result<>(destinationSearchService.findAllByKeyword(userId, keyword, PageRequest.of(page,perPage)));
    }
}
