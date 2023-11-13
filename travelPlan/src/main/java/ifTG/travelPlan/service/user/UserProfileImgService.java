package ifTG.travelPlan.service.user;


import ifTG.travelPlan.controller.dto.ProfileImgDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileImgService {
    ProfileImgDto saveProfileImg(MultipartFile file, Long userId);

    String getOriginalProfileImgUrl(Long userId, String fileName);
    ProfileImgDto getOriginalProfileImgUrl(Long userId);

    String getProfileImgUrl(Long userId, String fileName);

    Boolean deleteProfileImg(Long userId);

    ProfileImgDto updateProfileImg(MultipartFile file, Long userId);
}
