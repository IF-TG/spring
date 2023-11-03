package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestScrapDetail;
import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.post.PostScrapId;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.PostLikeRepository;
import ifTG.travelPlan.repository.springdata.PostScrapRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional
public class PostScrapServiceImpl implements PostScrapService{
    private final PostScrapRepository postScrapRepository;
    private final PostConvertDto postConvertDto;
    private final PostLikeRepository postLikeRepository;
    @Override
    public ToggleDto togglePostScrap(RequestScrapDto dto) {
       PostScrapId postScrapId = new PostScrapId(dto.getObjectId(), dto.getUserId());
       Optional<PostScrap> postScrap = postScrapRepository.findById(postScrapId);
       if (postScrap.isEmpty()){
           PostScrap newPostScrap = new PostScrap(postScrapId, dto.getFolderName());
           postScrapRepository.save(newPostScrap);
           return new ToggleDto(postScrapId.getPostId(), true);
       }else{
           postScrapRepository.delete(postScrap.get());
           return new ToggleDto(postScrapId.getPostId(), false);
       }
    }

    @Override
    public ScrapDto updateFolderName(RequestScrapDto dto) {
        PostScrapId postScrapId = new PostScrapId(dto.getObjectId(), dto.getUserId());
        PostScrap postScrap = postScrapRepository.findById(postScrapId).orElseThrow(()->new NullPointerException("해당 PostScrap 를 찾지 못했습니다."));
        postScrapRepository.save(postScrap.updateFolderName(dto.getFolderName()));
        return new ScrapDto(
                postScrap.getPost().getId(),
                postScrap.getUser().getId(),
                postScrap.getFolderName());
    }

    @Override
    public List<PostDto> findAllPostScrapsByScrapFolderAndUserId(RequestScrapDetail dto) {
        Slice<PostScrap> postScrapList = postScrapRepository.findAllWithPostByFolderNameAndUserId(dto.getFolderName(), dto.getUserId(), dto.getPageable());
        List<Long> likedPostList = postLikeRepository.findPostIdByUserId(dto.getUserId());
        return postConvertDto.getPostDtoList(postScrapList.stream().map(PostScrap::getPost).toList(), likedPostList);
    }
}
