package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.RequestPostListDto;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import ifTG.travelPlan.dto.post.enums.OrderMethod;
import ifTG.travelPlan.service.post.PostService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostListController {
    private final PostService postService;

    @GetMapping()
    public ResponseEntity<Result<List<PostWithThumbnailDto>>> getPostListWithCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam OrderMethod orderMethod,
            @RequestParam MainCategory mainCategory,
            @RequestParam @Nullable String subCategory,
            @AuthenticationUser Optional<Long> userId) throws IllegalAccessException {
        if (userId.isPresent()){
            return Result.isSuccess(postService.findAllPostWithPostRequestDto(userId.get(), new RequestPostListDto(page, perPage, orderMethod, mainCategory, subCategory)));
        }else{
            return Result.isSuccess(postService.findAllPostWithPostRequestDto(new RequestPostListDto(page, perPage, orderMethod, mainCategory, subCategory)));
        }

    }
}
