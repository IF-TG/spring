package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.service.user.UserProfileImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class UserProfileImgController {
    private final UserProfileImgService userProfileImgService;

    @PostMapping("/upload")
    public Boolean saveProfileImg(@RequestParam("profile")MultipartFile file,
                                  @RequestParam("userId")Long userId){
        return userProfileImgService.saveProfileImg(file, userId);
    }

    @PutMapping("/upload")
    public Boolean updateProfileImg(@RequestParam("profile")MultipartFile file,
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
