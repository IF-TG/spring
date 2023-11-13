package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.UserCreateDto;
import ifTG.travelPlan.controller.dto.UserInfoDto;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserInfoDto saveUser(UserCreateDto userCreateDto) {
        return null;
    }

    @Override
    public Boolean patchNickname(String nickname, Long userId) {
        if (!userRepository.existsByNickname(nickname)){
            userRepository.updateNickname(userId, nickname);
            return true;
        }
        return false;
    }

    @Override
    public Boolean isDuplicateNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }
}
