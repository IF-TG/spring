package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestSearchPostDto;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.repository.querydsl.QPostSearchRepository;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.user.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostSearchServiceImpl implements PostSearchService{
    private final QPostSearchRepository qPostSearchRepository;
    private final UserRepository userRepository;
    private final PostConvertDto postConvertDto;
    private final UserSearchService userSearchService;
    private final UserBlockRepository userBlockRepository;

    @Override
    public List<PostDto> findAllLikeKeyword(RequestSearchPostDto requestSearchPostDto) {
        User user = userRepository.findByIdWithPostLike(requestSearchPostDto.getUserId());
        List<Long> allBlockUserList = getAllBlockUserList(user);
        List<Long> likedPostListByUser = user.getPostLikeList().stream().map(PostLike::getPostLikedId).toList();
        Page<Post> postList = qPostSearchRepository.findAllByKeywordNotInBlockedUserId(
                requestSearchPostDto.getPageable(),
                requestSearchPostDto.getKeyword(),
                requestSearchPostDto.isContent(),
                requestSearchPostDto.isTitle(),
                allBlockUserList
                );
        new Thread(()->userSearchService.saveKeywordHistory(user, requestSearchPostDto.getKeyword())).start();
        return postConvertDto.getPostDtoList(postList, likedPostListByUser);
    }

    private List<Long> getAllBlockUserList(User user) {
        return userBlockRepository.findUserIdListByBlockedUserIdAndBlockingUserId(user.getId());
    }
}
