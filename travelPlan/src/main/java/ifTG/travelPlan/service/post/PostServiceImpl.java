package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.StatusCode;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostCategory;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.post.*;
import ifTG.travelPlan.dto.post.enums.MainCategory;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.querydsl.QPostListRepository;
import ifTG.travelPlan.repository.springdata.post.PostLikeRepository;
import ifTG.travelPlan.repository.springdata.post.PostCategoryRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.filestore.PostImgFileService;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 결합도 낮출것, 그러니까 클래스 좀 더 나누자 나중에
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class PostServiceImpl implements PostService{
    private final QPostListRepository qPostListRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCategoryRepository postCategoryRepository;
    private final PostConvertDto postConvertDto;
    private final PostImgFileService postImgFileService;
    private final PostLikeRepository postLikeRepository;
    private final UserBlockRepository userBlockRepository;

    @Override
    public List<PostWithThumbnailDto> findAllPostWithPostRequestDto(Long userId, RequestPostListDto requestPostListDto){
        User user = userRepository.findByIdWithPostLike(userId);
        List<Long> allBlockUserList = getAllBlockUserList(user);
        List<Long> likedPostListByUser = user.getPostLikeList().stream().map(PostLike::getPostLikedId).toList();
        Page<Post> postList = findAllPostsBySubCategoryOrderByOrderMethod(requestPostListDto, allBlockUserList);
        return postConvertDto.getPostWithThumbnailDtoList(postList, likedPostListByUser);
    }

    @Override
    public List<PostWithThumbnailDto> findAllPostWithPostRequestDto(RequestPostListDto requestPostListDto) {
        Page<Post> postList = findAllPostsBySubCategoryOrderByOrderMethod(requestPostListDto, new ArrayList<>());
        return postConvertDto.getPostWithThumbnailDtoList(postList, new ArrayList<>());
    }

    private List<Long> getAllBlockUserList(User user) {
        return userBlockRepository.findUserIdListByBlockedUserIdAndBlockingUserId(user.getId());
    }

    @Override
    @Transactional
    public PostDto savePost(Long userId, PostCreateDto postCreateDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        Post post = getPostByPostCreateDto(postCreateDto, user);
        Post savedPost = postRepository.save(post);
        saveAllPostSubCategoryByPostCreateDto(postCreateDto, savedPost);
        saveImgFile(postCreateDto.getImgFileList(), savedPost);
        return postConvertDto.getPostDto(postRepository.save(savedPost), false);
    }

    private void saveImgFile(List<ImgFile> imgFileList, Post savedPost) {
        postImgFileService.saveImgFile(imgFileList, savedPost);
    }

    @Override
    @Transactional
    public PostDto updatePost(Long userId, PostUpdateDto postUpdateDto) {
        Long postId = postUpdateDto.getPostId();
        deleteSubCategory(postId);
        Post post = postRepository.findByIdWithUserAndPostImgAndPostCategory(postUpdateDto.getPostId()).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(!post.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        updatePost(postUpdateDto, post);
        postImgFileService.updateImgFile(postUpdateDto.getPost().getImgFileList(), post);
        saveAllPostSubCategoryByPostCreateDto(postUpdateDto.getPost(), post);
        postRepository.save(post);
        return postConvertDto.getPostDto(post, false);
    }


    @Override
    public List<PostDto> findByUserId(Long userId, Pageable pageable) {
        Page<Post> post = postRepository.findAllWithUserByUserId(userId, pageable);
        List<Long> likedPostIdList = postLikeRepository.findPostIdByUserId(userId);
        return postConvertDto.getPostDtoList(post, likedPostIdList);
    }

    @Override
    public List<PostWithThumbnailDto> findCommentedOrLikedPostListByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findByIdWithPostLike(userId);
        List<Long> allBlockUserList = getAllBlockUserList(user);
        Page<Post> postList;
        postList = allBlockUserList.isEmpty() ?
                postRepository.findCommentedOrLikedPostListByUserId(userId, pageable):
                postRepository.findCommentedOrLikedPostListNotInBlockUserByUserId(userId, allBlockUserList, pageable);
        List<Long> likedPostIdList = user.getPostLikeList().stream().map(p->p.getPostLikeId().getPostId()).toList();
        return postConvertDto.getPostWithThumbnailDtoList(postList, likedPostIdList);
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
                postDto.getEndDate(),
                postDto.getMapX(),
                postDto.getMapY()
        );
    }

    @Override
    @Transactional
    public Boolean deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Check.is(!post.getUser().getId().equals(userId), StatusCode.AUTHORITY_FAILED);
        postImgFileService.deleteAllById(postId);
        postRepository.delete(post);
        return true;
    }




    private static Post getPostByPostCreateDto(PostCreateDto postCreateDto, User user) {
        return Post.builder()
                   .title(postCreateDto.getTitle())
                   .content(postCreateDto.getContent())
                   .startDate(postCreateDto.getStartDate())
                   .endDate(postCreateDto.getEndDate())
                   .user(user)
                   .mapX(postCreateDto.getMapX())
                   .mapY(postCreateDto.getMapY())
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
