package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.repository.querydsl.QPostSearchRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostSearchServiceImpl implements PostSearchService{
    private final QPostSearchRepository qPostSearchRepository;
    private final UserRepository userRepository;
    private final PostConvertDto postConvertDto;

    @Override
    public List<PostDto> findAllLikeKeyword(RequestSearchPostDto requestSearchPostDto) {
        User user = userRepository.findByUserIdWithUserBlockAndPostLike(requestSearchPostDto.getUserId());
        List<Long> userBlockIdList = user.getUserBlockList().stream().map(UserBlock::getBlockedUserId).toList();
        List<Long> likedPostList = user.getPostLikeList().stream().map(PostLike::getPostLikedId).toList();
        Page<Post> postList = qPostSearchRepository.findAllByKeywordNotInBlockedUserId(
                requestSearchPostDto.getPageable(),
                requestSearchPostDto.getKeyword(),
                requestSearchPostDto.isContent(),
                requestSearchPostDto.isTitle(),
                userBlockIdList
                );
        return postConvertDto.getPostDtoList(postList, likedPostList);
    }
}
