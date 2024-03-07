package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestRenameScrapFolder;
import ifTG.travelPlan.domain.post.PostImg;
import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.travel.DestinationScrap;
import ifTG.travelPlan.dto.user.UserScrapFolderDto;
import ifTG.travelPlan.repository.springdata.post.PostScrapRepository;
import ifTG.travelPlan.repository.springdata.post.PostImgRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.service.filestore.PostImgFileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UserScrapServiceImpl implements UserScrapService {
    private final PostScrapRepository postScrapRepository;
    private final DestinationScrapRepository destinationScrapRepository;

    @Getter
    static class CountAndThumbnail{
        private Integer count;
        private final List<String> thumbnailUri = new ArrayList<>();

        public CountAndThumbnail(){
            this.count = 1;
        }
        public CountAndThumbnail increaseCount(){
            this.count++;
            return this;
        }
        public void addThumbnailUri(String uri){
            this.thumbnailUri.add(uri);
        }
    }

    @Override
    public List<UserScrapFolderDto> findAllScrapFolderByUser(Long userId) {
        List<PostScrap> postScrapList = postScrapRepository.findAllByUserId(userId);
        List<DestinationScrap> destinationScrapList = destinationScrapRepository.findAllWithDestinationByUserId(userId);
        Map<String, CountAndThumbnail> scrapFolder = new HashMap<>();

        postScrapList.forEach(ps->{
            if (scrapFolder.containsKey(ps.getFolderName())){
                CountAndThumbnail ct = scrapFolder.get(ps.getFolderName());
                ct.increaseCount();
            }else{
                CountAndThumbnail ct = new CountAndThumbnail();
                scrapFolder.put(ps.getFolderName(), ct);
            }
        });
        destinationScrapList.forEach(ds->{
            if (scrapFolder.containsKey(ds.getFolderName())){
                CountAndThumbnail ct = scrapFolder.get(ds.getFolderName());
                ct.increaseCount();
                ct.addThumbnailUri(ds.getDestination().getThumbNail());
            }else{
                CountAndThumbnail ct = new CountAndThumbnail();
                ct.addThumbnailUri(ds.getDestination().getThumbNail());
                scrapFolder.put(ds.getFolderName(), ct);
            }
        });

        return scrapFolder.keySet().stream().map(sf->new UserScrapFolderDto(sf, scrapFolder.get(sf).count, scrapFolder.get(sf).thumbnailUri)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserScrapFolderDto> renameScrapFolder(Long userId, RequestRenameScrapFolder dto) {
        destinationScrapRepository.updateFolderName(userId, dto.getOldFolderName(), dto.getNewFolderName());
        postScrapRepository.updateFolderName(userId, dto.getOldFolderName(), dto.getNewFolderName());
        return findAllScrapFolderByUser(userId);
    }



}
