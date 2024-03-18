package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.service.post.PostSearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/search")
@SecurityRequirement(name = "Authorization")
public class PostSearchController {
    private final PostSearchService postSearchService;

    @GetMapping
    public ResponseEntity<Result<List<PostDto>>> getAllLikeKeyword(
            @RequestParam String keyword,
            @AuthenticationUser Optional<Long> userId,
            @RequestParam(defaultValue = "true") boolean isTitle,
            @RequestParam(defaultValue = "true") boolean isContent,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage){
        if (userId.isPresent()){
            return Result.isSuccess(postSearchService.findAllLikeKeyword(new RequestSearchPostDto(keyword, userId.get(), isTitle, isContent, page, perPage)));
        }else{
            return Result.isSuccess(postSearchService.findAllLikeKeyword(keyword, isTitle, isContent, page, perPage));
        }

    }

}
