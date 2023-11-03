package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.RequestSearchHistoryPageDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.controller.dto.SearchHistoryDto;
import ifTG.travelPlan.service.user.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searchHistory")
public class    UserSearchController {
    private final UserSearchService userSearchService;
    @GetMapping
    public Result<List<SearchHistoryDto>> findAllSearchHistoryByUser(@RequestBody RequestSearchHistoryPageDto requestSearchHistoryPageDto){
        return new Result<>(userSearchService.findAllSearchHistoryByUser(requestSearchHistoryPageDto));
    }
}
