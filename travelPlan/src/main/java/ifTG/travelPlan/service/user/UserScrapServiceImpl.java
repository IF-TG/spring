package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.controller.dto.RequestScrapFolderDto;
import ifTG.travelPlan.controller.user.RequestScrapDetail;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostImg;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.dto.ScrapPostAndDestination;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;
import ifTG.travelPlan.repository.springdata.PostLikeRepository;
import ifTG.travelPlan.repository.springdata.PostScrapRepository;
import ifTG.travelPlan.repository.springdata.post.PostImgRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.filestore.PostImgFileService;
import ifTG.travelPlan.service.post.PostConvertDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserScrapServiceImpl implements UserScrapService {
    static class CountAndThumbnail{
        private Integer count;
        private String thumbnailUri;

        public CountAndThumbnail(String thumbnailUri){
            this.thumbnailUri = thumbnailUri;
            this.count = 1;
        }
        public void increaseCount(){
            this.count++;
        }
    }
    private final PostScrapRepository postScrapRepository;
    private final DestinationScrapRepository destinationScrapRepository;
    private final PostImgRepository postImgRepository;
    private final PostImgFileService postImgFileService;
    private final PostConvertDto postConvertDto;
    private final PostLikeRepository postLikeRepository;
    public List<UserScrapFolderDto> findAllScrapFolderByUser(RequestScrapFolderDto dto) {
        List<PostScrap> postScrapList = postScrapRepository.findAllByUserId(dto.getUserId());
        List<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllWithDestinationByUserId(dto.getUserId());


        Map<String, CountAndThumbnail> scrapFolder = new HashMap<>();

        destinationScrapList.forEach(ds->{
            if (scrapFolder.containsKey(ds.getFolderName())){
                CountAndThumbnail updateCT = scrapFolder.get(ds.getFolderName());
                updateCT.increaseCount();
                if (updateCT.thumbnailUri.isEmpty()){
                    updateCT.thumbnailUri = ds.getDestination().getThumbNail();
                }
            }else{
                scrapFolder.put(ds.getFolderName(), new CountAndThumbnail(ds.getDestination().getThumbNail()));
            }
        });

        postScrapList.forEach(ps-> {
            if (scrapFolder.containsKey(ps.getFolderName())){
                scrapFolder.get(ps.getFolderName()).increaseCount();
                if (scrapFolder.get(ps.getFolderName()).thumbnailUri.isEmpty()){
                    Optional<PostImg> postImg =  postImgRepository.findFirstByPostIdAndIsThumbnail(ps.getPost().getId(), true);
                    postImg.ifPresent(img -> scrapFolder.get(ps.getFolderName()).thumbnailUri = postImgFileService.getPostThumbnailUrl(
                            img.getPost().getId(), img.getFileName()
                    ));
                }
            }else{
                scrapFolder.put(ps.getFolderName(), new CountAndThumbnail(null));
                Optional<PostImg> postImg =  postImgRepository.findFirstByPostIdAndIsThumbnail(ps.getPost().getId(), true);
                postImg.ifPresent(img -> scrapFolder.get(ps.getFolderName()).thumbnailUri = postImgFileService.getPostThumbnailUrl(
                        img.getPost().getId(), img.getFileName()
                ));
            }
        });

        return scrapFolder.keySet().stream().map(sf -> new UserScrapFolderDto(sf, scrapFolder.get(sf).count, scrapFolder.get(sf).thumbnailUri)).toList();
    }

    @Override
    public List<ScrapPostAndDestination> findAllScrapsByScrapFolderAndUserId(RequestScrapDetail dto) {
        Slice<PostScrap> postScrapList = postScrapRepository.findAllWithPostByFolderNameAndUserId(dto.getFolderName(), dto.getUserId(), dto.getPageable());
        Slice<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllWithDestinationByFolderNameAndUserId(dto.getFolderName(), dto.getUserId(), dto.getPageable());
       List<Long> likedPostList = postLikeRepository.findPostIdByUserId(dto.getUserId());

        /**
         * destiantion dto 확정시 업데이트 필요
         */
        List<PostDto> postDtoList = postConvertDto.getPostDtoList(postScrapList.stream().map(ps->ps.getPost()).toList(), likedPostList);

        return null;
    }
}
