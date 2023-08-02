package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostCategory;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public class PostConvertDtoImpl implements PostConvertDto{
    @Override
    public List<PostDto> getPostDtoList(Page<Post> postList, List<Long> likedPostIdList) {
        return postList.stream()
                       .map(p->getPostDto(p, isLiked(likedPostIdList, p)))
                       .toList();
    }
    @Override
    public List<PostDto> getPostDtoList(List<Post> postList, List<Long> likedPostIdList) {
        return postList.stream()
                       .map(p->getPostDto(p, isLiked(likedPostIdList, p)))
                       .toList();
    }

    @Override
    public PostDto getPostDto(Post post, boolean isLiked){
        List<String> seasons = getPostCategoryFilterMainCategory(post, MainCategory.SEASON);
        List<String> regions = getPostCategoryFilterMainCategory(post, MainCategory.REGION);
        List<String> themes = getPostCategoryFilterMainCategory(post, MainCategory.THEME);
        List<String> companions = getPostCategoryFilterMainCategory(post, MainCategory.COMPANION);
        return PostDto.builder()
                      .postId(post.getId())
                      .profileImgUri(post.getUser().getProfileImgUrl())
                      .title(post.getTitle())
                      .nickname(post.getUser().getNickname())
                      .startDate(post.getStartDate())
                      .endDate(post.getEndDate())
                      .postImgUri(post.getPostImgList())
                      .content(post.getContent())
                      .likeNum(post.getLikeNum())
                      .commentNum(post.getCommentNum())
                      .createAt(post.getCreateAt())
                      .seasons(seasons)
                      .companions(companions)
                      .regions(regions)
                      .themes(themes)
                      .isLiked(isLiked)
                      .build();
    }

    private static List<String> getPostCategoryFilterMainCategory(Post post, MainCategory mainCategory) {
        return post.getPostCategoryList().stream().filter(postCategory -> postCategory.getMainCategory().equals(mainCategory)).map(PostCategory::getSubCategory).toList();
    }

    private static boolean isLiked(List<Long> likedPostIdListByUser, Post p) {
        return likedPostIdListByUser.contains(p.getId());
    }
}
