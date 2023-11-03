package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostCategory;
import ifTG.travelPlan.domain.post.PostImg;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import ifTG.travelPlan.service.filestore.PostImgFileService;
import ifTG.travelPlan.service.user.UserProfileImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostConvertDtoImpl implements PostConvertDto{
    private final PostImgFileService postImgFileService;
    private final UserProfileImgService userProfileImgService;
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
    public List<PostWithThumbnailDto> getPostWithThumbnailDtoList(Page<Post> postList, List<Long> likedPostListByUser) {
        return postList.stream()
                .map(
                        post -> PostWithThumbnailDto.builder()
                                                .thumbnailUri(
                                                    postImgFileService.getPostThumbnailUrlList(post.getId(), post.getPostImgList().stream().filter(PostImg::isThumbnail).map(PostImg::getFileName).toList())
                                            )
                                                .post(getPostDto(post, isLiked(likedPostListByUser, post)))
                                                .build()
                ).toList();
    }

    @Override
    public List<PostWithThumbnailDto> getPostDtoListIsAllLike(List<Post> postList) {
        return postList.stream()
                               .map(
                                       post -> PostWithThumbnailDto.builder()
                                                                   .thumbnailUri(
                                                                           postImgFileService.getPostThumbnailUrlList(post.getId(), post.getPostImgList().stream().filter(PostImg::isThumbnail).map(PostImg::getFileName).toList())
                                                                   )
                                                                   .post(getPostDto(post, true))
                                                                   .build()
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
                      .profileImgUri(userProfileImgService.getProfileImgUrl(post.getUser().getId(), post.getUser().getProfileImgUrl()))
                      .title(post.getTitle())
                      .nickname(post.getUser().getNickname())
                      .startDate(post.getStartDate())
                      .endDate(post.getEndDate())
                      .postImgUri(postImgFileService.getPostImageListUrl(post.getId(), post.getPostImgList().stream().map(PostImg::getFileName).toList()))
                      .content(post.getContent())
                      .likeNum(post.getLikeNum())
                      .commentNum(post.getCommentNum())
                      .createAt(post.getCreateAt())
                      .seasons(seasons)
                      .companions(companions)
                      .regions(regions)
                      .themes(themes)
                      .isLiked(isLiked)
                      .mapX(post.getMapX())
                      .mapY(post.getMapY())
                      .build();
    }



    private static List<String> getPostCategoryFilterMainCategory(Post post, MainCategory mainCategory) {
        return post.getPostCategoryList().stream().filter(postCategory -> postCategory.getMainCategory().equals(mainCategory)).map(PostCategory::getSubCategory).toList();
    }

    private static boolean isLiked(List<Long> likedPostIdListByUser, Post p) {
        return likedPostIdListByUser.contains(p.getId());
    }
}
