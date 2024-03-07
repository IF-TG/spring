package ifTG.travelPlan.controller.user;

import ifTG.travelPlan.aop.AuthenticationUser;
import ifTG.travelPlan.controller.dto.ProfileImgDto;
import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.service.user.UserProfileImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileImgController {
    private final UserProfileImgService userProfileImgService;

    @PostMapping("/upload")
    public ResponseEntity<Result<ProfileImgDto>> saveProfileImg(@RequestParam("profile")MultipartFile file,
                                                                @AuthenticationUser Long userId){
        return Result.isSuccess(userProfileImgService.saveProfileImg(file, userId));
    }

    @PutMapping("/upload")
    public ResponseEntity<Result<ProfileImgDto>> updateProfileImg(@RequestParam("profile")MultipartFile file,
                                                                  @AuthenticationUser Long userId){
        return Result.isSuccess(userProfileImgService.updateProfileImg(file, userId));
    }

    @DeleteMapping
    public ResponseEntity<Result<Boolean>> deleteProfileImg(@AuthenticationUser Long userId){
        return Result.isSuccess(userProfileImgService.deleteProfileImg(userId));
    }

    @GetMapping("/original")
    public ResponseEntity<Result<ProfileImgDto>> getOriginalFileImg(@RequestParam Long userId){
        return Result.isSuccess(userProfileImgService.getOriginalProfileImgUrl(userId));
    }
}
