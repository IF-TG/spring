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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserBlockServiceImpl implements UserBlockService{
    private final UserBlockRepository userBlockRepository;
    private final UserProfileImgService userProfileImgService;
    @Override
    @Transactional
    public ToggleDto toggleBlockUser(Long userId, RequestBlockUserDto dto) {
        log.info("{}, {}", userId, dto.getBlockedUserId());
        UserBlockId userBlockId = new UserBlockId(userId, dto.getBlockedUserId());
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
    public List<NicknameAndThumbnail> getAllBlockedUserListByUser(Long userId) {
        List<UserBlock> userBlockList = userBlockRepository.findAllWithUserByUserId(userId);
        return userBlockList.stream().map(ub->new NicknameAndThumbnail(ub.getUser().getNickname(),
                userProfileImgService.getProfileImgUrl(ub.getUser().getId(),ub.getUser().getProfileImgUrl()))).toList();
    }


}
