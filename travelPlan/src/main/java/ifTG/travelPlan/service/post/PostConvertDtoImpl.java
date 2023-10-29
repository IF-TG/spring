package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostCategory;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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
    public List<PostWithThumbnailDto> getPostWithThumbnailDtoList(Page<Post> postList, Map<Long, List<ImageToString>> thumbnailMap, List<Long> likedPostListByUser) {
        return postList.stream()
                .filter(
                        post -> thumbnailMap.containsKey(post.getId())
                )
                .map(
                        post -> {
                            return PostWithThumbnailDto.builder()
                                                .thumbnail(thumbnailMap.get(post.getId()))
                                                .post(getPostDto(post, isLiked(likedPostListByUser, post)))
                                    .build();
                        }
                ).toList();
    }

    private List<ImageToString> getImageToStringDtoList(Map<String, ImageType> thumbNailWithImageType){
        return thumbNailWithImageType.keySet().stream()
                                     .map(thumbnail -> new ImageToString(thumbnail, thumbNailWithImageType.get(thumbnail))).toList();
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
