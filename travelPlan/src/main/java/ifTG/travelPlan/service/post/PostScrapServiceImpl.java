package ifTG.travelPlan.service.post;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestScrapDto;
import ifTG.travelPlan.controller.dto.RequestUpdatePostScrapDto;
import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.post.PostScrapId;
import ifTG.travelPlan.dto.ScrapDto;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.PostLikeRepository;
import ifTG.travelPlan.repository.springdata.PostScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostScrapServiceImpl implements PostScrapService{
    private final PostScrapRepository postScrapRepository;
    private final PostConvertDto postConvertDto;
    private final PostLikeRepository postLikeRepository;
    @Override
    @Transactional
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
    @Transactional
    public List<ScrapDto> updateFolderName(RequestUpdatePostScrapDto dto) {
        List<PostScrapId> postScrapIdList = dto.getObjectIdList().stream().map(d->new PostScrapId(d, dto.getUserId())).toList();
        List<PostScrap> postScrapList = postScrapRepository.findAllById(postScrapIdList);
        postScrapList.forEach(ps->ps.updateFolderName(dto.getFolderName()));
        postScrapRepository.saveAll(postScrapList);
        return postScrapList.stream().map(ps->new ScrapDto(
                ps.getPost().getId(),
                ps.getUser().getId(),
                ps.getFolderName())).toList();
    }

    @Override
    public List<PostDto> findAllPostScrapsByScrapFolderAndUserId(String folderName, Long userId, Pageable pageable) {
        Slice<PostScrap> postScrapList = postScrapRepository.findAllWithPostByFolderNameAndUserId(folderName, userId, pageable);
        List<Long> likedPostList = postLikeRepository.findPostIdByUserId(userId);
        return postConvertDto.getPostDtoList(postScrapList.stream().map(PostScrap::getPost).toList(), likedPostList);
    }
}
