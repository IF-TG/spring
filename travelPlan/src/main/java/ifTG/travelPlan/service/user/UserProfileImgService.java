package ifTG.travelPlan.service.user;


import ifTG.travelPlan.controller.user.ProfileImgDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileImgService {
    Boolean saveProfileImg(MultipartFile file, Long userId);

    String getOriginalProfileImgUrl(Long userId, String fileName);
    ProfileImgDto getOriginalProfileImgUrl(Long userId);

    String getProfileImgUrl(Long userId, String fileName);

    Boolean deleteProfileImg(Long userId);

    Boolean updateProfileImg(MultipartFile file, Long userId);
}
