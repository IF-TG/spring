package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestCreateScrapFolderDto;
import ifTG.travelPlan.controller.dto.RequestGetAllScrapByUserDto;
import ifTG.travelPlan.domain.user.ScrapFolder;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.user.ScrapFolderDto;
import ifTG.travelPlan.dto.user.ScrapTitleDto;
import ifTG.travelPlan.dto.user.ScrapType;
import ifTG.travelPlan.repository.springdata.PostScrapRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.ScrapFolderRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.filestore.PostImgFileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserScrapServiceImpl implements UserScrapService{
    private final PostScrapRepository postScrapRepository;
    private final UserRepository userRepository;
    private final ScrapFolderRepository scrapFolderRepository;
    private final PostRepository postRepository;
    private final PostImgFileService postImgFileService;
    @Override
    public ScrapTitleDto createScrapFolder(RequestCreateScrapFolderDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(EntityNotFoundException::new);
        ScrapFolder scrapFolder = scrapFolderRepository.save(new ScrapFolder(user, dto.getTitle()));
        return new ScrapTitleDto(scrapFolder.getFolderName());
    }

    /**
     * destination 업데이트 시 변경해야함
     */
    @Override
    public List<ScrapFolderDto> findAllScrapByUser(RequestGetAllScrapByUserDto dto) {
        List<ScrapFolder> scrapFolderList = scrapFolderRepository.findAllWithScrapImgListByUserId(dto.getUserId());
        Map<ScrapFolder, List<ImageToString>> scrapFolderImageListMap = scrapFolderList.stream().collect(Collectors.toMap(
                scrapFolder -> scrapFolder,
                scrapFolder ->  scrapFolder.getScrapFolderImgList().stream().filter(
                        scrapFolderImg -> scrapFolderImg.getScrapType().equals(ScrapType.post)
                ).map(
                        scrapFolderImg -> postImgFileService.getPostThumbnailByFilename(scrapFolderImg.getFileName())
                ).toList()
        ));
        return scrapFolderImageListMap.keySet().stream().map(
                scrapFolder -> new ScrapFolderDto(scrapFolder.getFolderName(), scrapFolderImageListMap.get(scrapFolder))
        ).toList();
    }
}
