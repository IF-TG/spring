package ifTG.travelPlan.service.user;

import ifTG.travelPlan.controller.dto.ProfileImgDto;
import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.filestore.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class UserProfileImgServiceImpl implements UserProfileImgService{
    private final UserRepository userRepository;
    private final FileStore fileStore;
    @Value("${profile.path}")
    private String profilePath;
    @Value("${profile.thumbnail.length}")
    private Integer length;
    @Value("${thumbnail.path}")
    private String thumbnailPath;
    @Value("${IMG_SERVER_URL}")
    private String imgServerUrl;

    @Override
    public ProfileImgDto saveProfileImg(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        if (user.getProfileImgUrl()!=null){
            return updateProfileByUser(file, user);
        }
        return saveProfileImgByUser(file, user);
    }

    private ProfileImgDto saveProfileImgByUser(MultipartFile file, User user) {
        String uri = getProfileUri(user.getId());
        try{
            String extension = fileStore.getExtension(file.getOriginalFilename());
            String savedFileName = fileStore.saveFile(file.getBytes(), uri, extension);
            fileStore.createThumbnailAndSaveFile(uri, savedFileName, thumbnailPath, length);
            user.updateProfileImgUrl(savedFileName);
            return new ProfileImgDto(getProfileImgUrl(user.getId(), savedFileName), user.getId());
        }catch(IOException e){
            log.debug("", e);
            throw new CustomErrorException(StatusCode.Internal_Server_Error);
        }
    }

    private String getProfileUri(Long userId) {
        return profilePath + userId + "\\";
    }

    @Override
    public String getOriginalProfileImgUrl(Long userId, String fileName){
        return imgServerUrl + getProfileUri(userId) + fileName;
    }

    @Override
    public ProfileImgDto getOriginalProfileImgUrl(Long userId) {
        Optional<String> profileImgByUserId = userRepository.findProfileImgByUserId(userId);
        if (profileImgByUserId.isPresent()){
            String originalProfileImgUrl = getOriginalProfileImgUrl(userId, profileImgByUserId.get());
            return new ProfileImgDto(originalProfileImgUrl, userId);
        }else{
            return new ProfileImgDto(null, userId);
        }

    }

    @Override
    public String getProfileImgUrl(Long userId, String fileName){
        return fileName==null?null:imgServerUrl + getProfileUri(userId) + thumbnailPath + fileName;
    }

    @Override
    public Boolean deleteProfileImg(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
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
    public ProfileImgDto updateProfileImg(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_USER));
        return updateProfileByUser(file, user);
    }

    private ProfileImgDto updateProfileByUser(MultipartFile file, User user) {
        Long userId = user.getId();
        if (user.getProfileImgUrl()==null) return saveProfileImgByUser(file, user);
        else{
            try{
                byte[] inputFilePath = fileStore.getHash(file.getBytes());
                byte[] fileHash = fileStore.getHash(getProfileUri(userId) + thumbnailPath + user.getProfileImgUrl());
                if (Arrays.equals(inputFilePath, fileHash))
                    return new ProfileImgDto(getProfileImgUrl(userId, user.getProfileImgUrl()), userId);
                else{
                    deleteProfileImgByUser(user);
                    return saveProfileImgByUser(file, user);
                }
            }catch(IOException e){
                log.debug("", e);
                throw new CustomErrorException(StatusCode.Internal_Server_Error);
            }
        }
    }
}
