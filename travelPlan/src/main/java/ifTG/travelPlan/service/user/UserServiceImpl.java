package ifTG.travelPlan.service.user;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Boolean patchNickname(String nickname, Long userId) {
        if (!userRepository.existsByNickname(nickname)){
            userRepository.updateNickname(userId, nickname);
            return true;
        }else{
            throw new CustomErrorException(StatusCode.DUPLICATE_NICKNAME);
        }

    }

    @Override
    public Boolean isDuplicateNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }
}
