package ifTG.travelPlan.controller.destination;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.service.destination.DestinationLikeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/destination/like")
public class DestinationLikeController {
    private final DestinationLikeService destinationLikeService;

    @PostMapping
    public ResponseEntity<Result<ToggleDto>> toggleLikeDestination(@AuthenticationUser Long userId, @RequestBody RequestLikeDto requestLikeDto) {
        return Result.isSuccess(destinationLikeService.toggleLike(requestLikeDto.getObjectId(), userId));
    }
}
