package ifTG.travelPlan.service.post;

import ifTG.travelPlan.domain.build.PostBuilder;
import ifTG.travelPlan.domain.post.*;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.post.PostCreateDto;
import ifTG.travelPlan.dto.post.PostDeleteDto;
import ifTG.travelPlan.dto.post.PostRequestDto;
import ifTG.travelPlan.dto.post.PostUpdateDto;
import ifTG.travelPlan.dto.post.enums.Companions;
import ifTG.travelPlan.dto.post.enums.Regions;
import ifTG.travelPlan.dto.post.enums.Seasons;
import ifTG.travelPlan.dto.post.enums.Themes;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.repository.querydsl.QPostRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * to do
 * 1 : post dto에 담아서 보내기
 * 5 : handle~ 함수들 중복 코드 ( 은근 enum 합치기 힘들다. )
 */
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService{
    private final QPostRepository qPostRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<PostDto> findAllPostWithPostRequestDto(PostRequestDto postRequestDto){
        User user = userRepository.findByUserIdWithUserBlockAndPostLike(postRequestDto.getUserId());
        log.info("{}", user.getUserId());
        List<Long> blockedUserIdList = user.getUserBlockList().stream().map(ub -> ub.getUser().getId()).toList();
        List<Long> likedPostId = user.getPostLikeList().stream().map(pl -> pl.getPostLikeId().getPostId()).toList();
        Page<Post> postList = findAllPostsBySubCategoryOrderByOrderMethod(postRequestDto, blockedUserIdList);
        return getPostDtoList(postList, likedPostId);
    }

    @Override
    public PostDto savePost(PostCreateDto postCreateDto) {
        User user = userRepository.findByUserId(postCreateDto.getUserId());
        Post post = getPostByPostCreateDto(postCreateDto, user);
        Post savedPost = postRepository.save(post);
        addTravelThemeToSavedPost(postCreateDto, savedPost);
        return getPostDto(postRepository.save(savedPost), false);
    }

    @Override
    public Boolean deletePost(PostDeleteDto postDeleteDto) {
        postRepository.deleteById(postDeleteDto.getPostId());
        return true;
    }

    @Override
    public PostDto updatePost(PostUpdateDto postUpdateDto) {
        log.info("updatePost = {}", postUpdateDto.getPostId());
        Post post = postRepository.findById(postUpdateDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        log.info("findById = {}", post.getId());
        post.updatePost(post.getTitle(), post.getContent(), post.getStartDate(), post.getEndDate());
        log.info("updatePost = {}", post.getTitle());
        post.clearSubCategory();
        log.info("clearSubCategory");
        addTravelThemeToSavedPost(postUpdateDto.getPost(), post);
        log.info("addSub");
        Post updatePost = postRepository.save(post);
        return getPostDto(updatePost, false);
    }

    private static Post getPostByPostCreateDto(PostCreateDto postCreateDto, User user) {
        Post post = Post.builder()
                        .title(postCreateDto.getTitle())
                        .content(postCreateDto.getContent())
                        .startDate(postCreateDto.getStartDate())
                        .endDate(postCreateDto.getEndDate())
                        .user(user)
                .build();
        return post;
    }

    private static void addTravelThemeToSavedPost(PostCreateDto postCreateDto, Post savedPost) {
        List<PostTheme> themes = postCreateDto.getThemes().stream().map(theme->new PostTheme(theme, savedPost)).toList();
        List<PostRegion> regions = postCreateDto.getRegions().stream().map(region->new PostRegion(region, savedPost)).toList();
        List<PostSeason> seasons = postCreateDto.getSeasons().stream().map(season->new PostSeason(season,savedPost)).toList();
        List<PostCompanion> companions = postCreateDto.getCompanions().stream().map(companion->new PostCompanion(companion, savedPost)).toList();
        savedPost.setTravelSubCategories(themes, regions, companions, seasons);
    }


    private Page<Post> findAllPostsBySubCategoryOrderByOrderMethod(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        Page<Post> postList;
        switch(postRequestDto.getMainCategory()){
            case ORIGINAL -> postList = handleOriginalEnumType(postRequestDto, blockedUserIdList);
            case SEASON -> postList = handleSeasonEnumType(postRequestDto, blockedUserIdList);
            case THEME -> postList = handleThemeEnumType(postRequestDto, blockedUserIdList);
            case REGION -> postList = handleRegionEnumType(postRequestDto, blockedUserIdList);
            case COMPANION -> postList = handleCompanionEnumType(postRequestDto, blockedUserIdList);
            default -> throw new RuntimeException("잘못된 요청 > MainCategory");
        }
        return postList;
    }

    private Page<Post> handleOriginalEnumType(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        /**
         * 기본일경우 일단 전체적으로 보여주는 것으로
         * 단 , 나중에 바꿀거임
         */
        return qPostRepository.findAll(
                postRequestDto.getPageable(),
                postRequestDto.getOrderMethod(),
                blockedUserIdList
        );
    }

    private Page<Post> handleCompanionEnumType(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        return qPostRepository.findAllWithCompanionBySubCategory(
                postRequestDto.getPageable(),
                postRequestDto.getOrderMethod(),
                (Companions) postRequestDto.getSubCategory(),
                blockedUserIdList
        );
    }

    private Page<Post> handleRegionEnumType(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        return qPostRepository.findAllWithRegionBySubCategory(
                postRequestDto.getPageable(),
                postRequestDto.getOrderMethod(),
                (Regions) postRequestDto.getSubCategory(),
                blockedUserIdList
        );
    }

    private Page<Post> handleThemeEnumType(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        return qPostRepository.findAllWithThemeBySubCategory(
                postRequestDto.getPageable(),
                postRequestDto.getOrderMethod(),
                (Themes) postRequestDto.getSubCategory(),
                blockedUserIdList
        );
    }

    private Page<Post> handleSeasonEnumType(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        return qPostRepository.findAllWithSeasonBySubCategory(
                postRequestDto.getPageable(),
                postRequestDto.getOrderMethod(),
                (Seasons) postRequestDto.getSubCategory(),
                blockedUserIdList
        );
    }

    private static List<PostDto> getPostDtoList(Page<Post> postList, List<Long> likedPostIdList) {
        return postList.stream()
                .map(p->getPostDto(p, likedPostIdList.contains(p.getId())))
                .toList();
    }

    private static PostDto getPostDto(Post post, boolean isLiked){
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
                      .seasons(post.getPostSeasonList().stream().map(PostSeason::getSeasons).toList())
                      .companions(post.getPostCompanionList().stream().map(PostCompanion::getCompanions).toList())
                      .regions(post.getPostRegionList().stream().map(PostRegion::getRegions).toList())
                      .themes(post.getPostThemeList().stream().map(PostTheme::getThemes).toList())
                      .isLiked(isLiked)
                      .build();
    }

}
