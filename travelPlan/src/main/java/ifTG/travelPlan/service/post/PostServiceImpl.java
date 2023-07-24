package ifTG.travelPlan.service.post;

import ifTG.travelPlan.domain.post.*;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.dto.post.enums.*;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.repository.querydsl.QPostRepository;
import ifTG.travelPlan.repository.springdata.post.PostCategoryRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * to do
 * 1 : post dto에 담아서 보내기 o
 * 2 : 서브 카테고리 레퍼지토리를 좀 줄일 수 없을까? 해당 레퍼지토리로 삭제하는게 아니라면 여러번의 delete문이 나가 성능저하 가능성
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
    private final PostCategoryRepository postCategoryRepository;


    @Override
    public List<PostDto> findAllPostWithPostRequestDto(PostRequestDto postRequestDto){
        User user = userRepository.findByUserIdWithUserBlockAndPostLike(postRequestDto.getUserId());
        log.info("{}", user.getUserId());
        List<Long> blockedUserIdList = user.getUserBlockList().stream().map(ub -> ub.getUser().getId()).toList();
        List<Long> likedPostIdByUser = user.getPostLikeList().stream().map(pl -> pl.getPostLikeId().getPostId()).toList();
        Page<Post> postList = findAllPostsBySubCategoryOrderByOrderMethod(postRequestDto, blockedUserIdList);
        return getPostDtoList(postList, likedPostIdByUser);
    }

    @Override
    public PostDto savePost(PostCreateDto postCreateDto) {
        User user = userRepository.findById(postCreateDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Post post = getPostByPostCreateDto(postCreateDto, user);
        Post savedPost = postRepository.save(post);
        saveAllPostSubCategoryByPostCreateDto(postCreateDto, savedPost);
        return getPostDto(postRepository.save(savedPost), false);
    }
    @Override
    public PostDto updatePost(PostUpdateDto postUpdateDto) {
        Long postId = postUpdateDto.getPostId();
        deleteSubCategory(postId);
        Post post = postRepository.findByIdWithUserAndPostImgAndPostCategory(postUpdateDto.getPostId()).orElseThrow(EntityNotFoundException::new);
        updatePost(postUpdateDto, post);
        saveAllPostSubCategoryByPostCreateDto(postUpdateDto.getPost(), post);
        postRepository.save(post);
        return getPostDto(post, false);
    }



    private void deleteSubCategory(Long postId) {
        postCategoryRepository.deleteAllByPostId(postId);
    }
    private static void updatePost(PostUpdateDto postUpdateDto, Post post) {
        PostCreateDto postDto = postUpdateDto.getPost();
        log.info("update Post = {}, {}, {}, {}", postDto.getTitle(), post.getContent(), postDto.getStartDate(), postDto.getEndDate());
        post.updatePost(
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getStartDate(),
                postDto.getEndDate()
        );
    }

    @Override
    public Boolean deletePost(PostIdDto postIdDto) {
        postRepository.deleteById(postIdDto.getPostId());
        return true;
    }




    private static Post getPostByPostCreateDto(PostCreateDto postCreateDto, User user) {
        return Post.builder()
                   .title(postCreateDto.getTitle())
                   .content(postCreateDto.getContent())
                   .startDate(postCreateDto.getStartDate())
                   .endDate(postCreateDto.getEndDate())
                   .user(user)
                   .build();
    }

    private void saveAllPostSubCategoryByPostCreateDto(PostCreateDto postCreateDto, Post savedPost) {
        Set<PostCategory> postCategoryList = savedPost.getPostCategoryList();
        postCategoryList.addAll(postCreateDto.getCompanions().stream().map(companion -> new PostCategory(savedPost, MainCategory.COMPANION, companion)).toList());
        postCategoryList.addAll(postCreateDto.getSeasons().stream().map(season -> new PostCategory(savedPost, MainCategory.SEASON, season)).toList());
        postCategoryList.addAll(postCreateDto.getRegions().stream().map(region -> new PostCategory(savedPost, MainCategory.REGION, region)).toList());
        postCategoryList.addAll(postCreateDto.getThemes().stream().map(themes -> new PostCategory(savedPost, MainCategory.THEME, themes)).toList());
    }


    private Page<Post> findAllPostsBySubCategoryOrderByOrderMethod(PostRequestDto postRequestDto, List<Long> blockedUserIdList) {
        return qPostRepository.findAllBySubCategoryOrderByOrderMethod(
                postRequestDto.getPageable(),
                postRequestDto.getOrderMethod(),
                postRequestDto.getSubCategory(),
                blockedUserIdList
        );
    }

    private static List<PostDto> getPostDtoList(Page<Post> postList, List<Long> likedPostIdList) {
        return postList.stream()
                .map(p->getPostDto(p, isLiked(likedPostIdList, p)))
                .toList();
    }

    private static boolean isLiked(List<Long> likedPostIdListByUser, Post p) {
        return likedPostIdListByUser.contains(p.getId());
    }

    private static PostDto getPostDto(Post post, boolean isLiked){
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

}
