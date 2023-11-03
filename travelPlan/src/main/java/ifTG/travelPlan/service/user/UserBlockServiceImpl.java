package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.RequestBlockUserDto;
import ifTG.travelPlan.controller.dto.RequestGetAllBlockedUserByUser;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.domain.user.UserBlockId;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.dto.user.NicknameAndThumbnail;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserBlockServiceImpl implements UserBlockService{
    private final UserBlockRepository userBlockRepository;
    private final UserProfileImgService userProfileImgService;
    @Override
    public ToggleDto toggleBlockUser(RequestBlockUserDto dto) {
        log.info("{}, {}", dto.getUserId(), dto.getBlockedUserId());
        UserBlockId userBlockId = new UserBlockId(dto.getUserId(), dto.getBlockedUserId());
        Optional<UserBlock> userBlock = userBlockRepository.findById(userBlockId);
        if (userBlock.isEmpty()){
            userBlockRepository.save(new UserBlock(userBlockId));
            return new ToggleDto(userBlockId.getBlockedUserId(), true);
        }else{
            userBlockRepository.delete(userBlock.get());
            return new ToggleDto(userBlockId.getBlockedUserId(), false);
        }
    }

    @Override
    public List<NicknameAndThumbnail> getAllBlockedUserListByUser(RequestGetAllBlockedUserByUser dto) {
        List<UserBlock> userBlockList = userBlockRepository.findAllWithUserByUserId(dto.getUserId());
        return userBlockList.stream().map(ub->new NicknameAndThumbnail(ub.getUser().getNickname(),
                userProfileImgService.getProfileImgUrl(ub.getUser().getId(),ub.getUser().getProfileImgUrl()))).toList();
    }


}
