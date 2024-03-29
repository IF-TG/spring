package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.SearchHistoryDto;
import ifTG.travelPlan.service.user.UserSearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searchHistory")
@SecurityRequirement(name = "Authorization")
public class UserSearchController {
    private final UserSearchService userSearchService;
    @GetMapping
    public ResponseEntity<Result<List<SearchHistoryDto>>> getAllSearchHistoryByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @AuthenticationUser Long userId){
        return Result.isSuccess(userSearchService.findAllSearchHistoryByUser(userId, PageRequest.of(page, perPage)));
    }
}
