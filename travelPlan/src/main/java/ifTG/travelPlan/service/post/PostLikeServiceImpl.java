package ifTG.travelPlan.service.post;

import ifTG.travelPlan.aop.ToggleLike;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.controller.dto.RequestPostListByUserIdDto;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.dto.post.PostWithThumbnailDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService{
    private final PostLikeRepository postLikeRepository;
    private final PostConvertDto postConvertDto;

    @Override
    @ToggleLike
    public ToggleDto toggleLikePost(RequestLikeDto requestLikeDto) {
        return null;
    }

    @Override
    public List<PostWithThumbnailDto> findAllPostLikeWithPostByUser(Long userId, Pageable pageable) {
        Page<PostLike> postLikeList = postLikeRepository.findAllWithPostByUserId(pageable, userId);
        return postConvertDto.getPostDtoListIsAllLike(postLikeList.stream().map(PostLike::getPost).toList());
    }
}
