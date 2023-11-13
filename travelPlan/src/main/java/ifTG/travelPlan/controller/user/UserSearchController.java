package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.SearchHistoryDto;
import ifTG.travelPlan.service.user.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searchHistory")
public class    UserSearchController {
    private final UserSearchService userSearchService;
    @GetMapping
    public Result<List<SearchHistoryDto>> findAllSearchHistoryByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam Long userId){
        return new Result<>(userSearchService.findAllSearchHistoryByUser(userId, PageRequest.of(page, perPage)));
    }
}
