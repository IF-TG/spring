package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.user.ProfileImgDto;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.filestore.FileStore;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
@Transactional
public class UserProfileImgServiceImpl implements UserProfileImgService{
    private final UserRepository userRepository;
    private final FileStore fileStore;
    @Value("${profile.path}")
    private String profilePath;

    @Value("${profile.length}")
    private Integer length;

    @Value("${thumbnail.path}")
    private String thumbnailPath;
    @Value("${IMG_SERVER_URL}")
    private String imgServerUrl;

    @Override
    public Boolean saveProfileImg(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("not found userid"));
        return saveProfileImgByUser(file, user);
    }

    private Boolean saveProfileImgByUser(MultipartFile file, User user) {
        String uri = getProfileUri(user.getId());
        try{
            String extension = fileStore.getExtension(file.getOriginalFilename());
            String savedFileName = fileStore.saveFile(file.getBytes(), uri, extension);
            fileStore.createThumbnailAndSaveFile(uri, savedFileName, thumbnailPath, length);
            user.updateProfileImgUrl(savedFileName);
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    private String getProfileUri(Long userId) {
        return profilePath + userId + "/";
    }

    @Override
    public String getOriginalProfileImgUrl(Long userId, String fileName){
        return imgServerUrl + getProfileUri(userId) + fileName;
    }

    @Override
    public ProfileImgDto getOriginalProfileImgUrl(Long userId) {
        String profileImgByUserId = userRepository.findProfileImgByUserId(userId);
        String originalProfileImgUrl = getOriginalProfileImgUrl(userId, profileImgByUserId);
        return new ProfileImgDto(originalProfileImgUrl, userId);
    }

    @Override
    public String getProfileImgUrl(Long userId, String fileName){
        return imgServerUrl + getProfileUri(userId) + thumbnailPath + fileName;
    }

    @Override
    public Boolean deleteProfileImg(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("not found userid"));
        deleteProfileImgByUser(user);
        return true;
    }

    private void deleteProfileImgByUser(User user) {
        String url = getProfileUri(user.getId()) + thumbnailPath + user.getProfileImgUrl();
        fileStore.deleteFile(url);
        String originalUrl = getProfileUri(user.getId()) + user.getProfileImgUrl();
        fileStore.deleteFile(originalUrl);
        user.updateProfileImgUrl(null);
    }

    @Override
    public Boolean updateProfileImg(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("not found userId"));
        if (user.getProfileImgUrl()==null)saveProfileImgByUser(file,user);
        else{
            try{
                byte[] inputFilePath = fileStore.getHash(file.getBytes());
                byte[] fileHash = fileStore.getHash(getProfileUri(userId) + thumbnailPath + user.getProfileImgUrl());
                if (Arrays.equals(inputFilePath, fileHash))return true;
                else{
                    deleteProfileImgByUser(user);
                    saveProfileImgByUser(file, user);
                }
            }catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
