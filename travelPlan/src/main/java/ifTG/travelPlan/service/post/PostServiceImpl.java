package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.domain.post.*;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.dto.post.enums.*;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.repository.querydsl.QPostListRepository;
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
 * 결합도 낮출것, 그러니까 클래스 좀 더 나누자 나중에
 */
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService{
    private final QPostListRepository qPostListRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCategoryRepository postCategoryRepository;
    private final PostConvertDto postConvertDto;

    @Override
    public List<PostDto> findAllPostWithPostRequestDto(RequestPostListDto requestPostListDto){
        User user = userRepository.findByUserIdWithUserBlockAndPostLike(requestPostListDto.getUserId());
        List<Long> blockedUserIdList = user.getUserBlockList().stream().map(UserBlock::getBlockedUserId).toList();
        List<Long> likedPostListByUser = user.getPostLikeList().stream().map(PostLike::getPostLikedId).toList();
        Page<Post> postList = findAllPostsBySubCategoryOrderByOrderMethod(requestPostListDto, blockedUserIdList);
        return postConvertDto.getPostDtoList(postList, likedPostListByUser);
    }

    @Override
    public PostDto savePost(PostCreateDto postCreateDto) {
        User user = userRepository.findById(postCreateDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Post post = getPostByPostCreateDto(postCreateDto, user);
        Post savedPost = postRepository.save(post);
        saveAllPostSubCategoryByPostCreateDto(postCreateDto, savedPost);
        return postConvertDto.getPostDto(postRepository.save(savedPost), false);
    }
    @Override
    public PostDto updatePost(PostUpdateDto postUpdateDto) {
        Long postId = postUpdateDto.getPostId();
        deleteSubCategory(postId);
        Post post = postRepository.findByIdWithUserAndPostImgAndPostCategory(postUpdateDto.getPostId()).orElseThrow(EntityNotFoundException::new);
        updatePost(postUpdateDto, post);
        saveAllPostSubCategoryByPostCreateDto(postUpdateDto.getPost(), post);
        postRepository.save(post);
        return postConvertDto.getPostDto(post, false);
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


    private Page<Post> findAllPostsBySubCategoryOrderByOrderMethod(RequestPostListDto requestPostListDto, List<Long> blockedUserIdList) {
        return qPostListRepository.findAllBySubCategoryOrderByOrderMethod(
                requestPostListDto.getPageable(),
                requestPostListDto.getOrderMethod(),
                requestPostListDto.getSubCategory(),
                blockedUserIdList
        );
    }







}
