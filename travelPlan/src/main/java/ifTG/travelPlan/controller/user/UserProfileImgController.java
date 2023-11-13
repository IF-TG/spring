package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.controller.dto.ProfileImgDto;
import ifTG.travelPlan.service.user.UserProfileImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class UserProfileImgController {
    private final UserProfileImgService userProfileImgService;

    @PostMapping("/upload")
    public ProfileImgDto saveProfileImg(@RequestParam("profile")MultipartFile file,
                                  @RequestParam("userId")Long userId){
        return userProfileImgService.saveProfileImg(file, userId);
    }

    @PutMapping("/upload")
    public ProfileImgDto updateProfileImg(@RequestParam("profile")MultipartFile file,
                                    @RequestParam("userId")Long userId){
        return userProfileImgService.updateProfileImg(file, userId);
    }

    @DeleteMapping
    public Boolean deleteProfileImg(@RequestParam Long userId){
        return userProfileImgService.deleteProfileImg(userId);
    }

    @GetMapping("/original")
    public ProfileImgDto getOriginalFileImg(@RequestParam Long userId){
        return userProfileImgService.getOriginalProfileImgUrl(userId);
    }
}
