package ifTG.travelPlan.controller.post;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.RequestPostListDto;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import ifTG.travelPlan.dto.post.enums.OrderMethod;
import ifTG.travelPlan.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostListController {
    private final PostService postService;

    @GetMapping()
    public Result<List<PostWithThumbnailDto>> getPostListWithCategory(
            @RequestParam int page,
            @RequestParam int perPage,
            @RequestParam OrderMethod orderMethod,
            @RequestParam MainCategory mainCategory,
            @RequestParam String subCategory,
            @RequestParam Long userId) throws IllegalAccessException {
        return new Result<>(postService.findAllPostWithPostRequestDto(new RequestPostListDto(page, perPage, orderMethod, mainCategory, subCategory, userId)));
    }
}
