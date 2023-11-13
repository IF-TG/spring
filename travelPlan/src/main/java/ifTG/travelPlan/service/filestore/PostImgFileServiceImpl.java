package ifTG.travelPlan.service.filestore;


import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostImg;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import ifTG.travelPlan.dto.post.ImgFile;
import ifTG.travelPlan.repository.springdata.post.PostImgRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostImgFileServiceImpl implements PostImgFileService {
    @Value("${path.post.path}")
    private String postPath;
    @Value("${thumbnail.path}")
    private String thumbnailPath;
    @Value("${IMG_SERVER_URL}")
    private String imgServerUrl;
    @Value("${thumbnail.length}")
    private Integer length;

    private final FileStore fileStore;
    private final PostImgRepository postImgRepository;

    @Override
    public void saveImgFile(List<ImgFile> imgFiles, Post post) {
        String postIdUri = getPostPath(post.getId());
        imgFiles.forEach(imgFile -> {
            String savedFileName = saveImgFileToFileStore(postIdUri, imgFile);
            postImgRepository.save(new PostImg(savedFileName, post, imgFile.isThumbnail(), imgFile.getSort()));
        });
    }

    private String saveImgFileToFileStore(String postIdUri, ImgFile imgFile) {
        String savedFileName = fileStore.saveFileToBase64Decode(imgFile.getImg(), postIdUri, imgFile.getImageType().toString());
        log.info("isthumbnail = {}", imgFile.isThumbnail());
        if(imgFile.isThumbnail()){
            new Thread(()->fileStore.createThumbnailAndSaveFile(postIdUri, savedFileName, thumbnailPath, length)).start();
        }
        return savedFileName;
    }

    @Getter
    @AllArgsConstructor
    private static class ImgFileName{
        private String fileName;
        private Integer sort;
        private Boolean isThumbnail;
    }
    @Override
    public void updateImgFile(List<ImgFile> imgFileList, Post post) {
        Map<String, PostImg> fileNamePostImgMap = post.getPostImgList().stream().collect(Collectors.toMap(PostImg::getFileName, pi->pi));

        String path = getPostPath(post.getId());

        Map<byte[], ImgFileName> hashBySavedFileMap = getHashBySavedFileMap(path, post);
        Map<byte[], ImgFile> hashByUpdateFileMap = getHashByUpdateFileMap(imgFileList);

        /**
         *java.util.ConcurrentModificationException 문제
         */
        Set<byte[]> hashBySavedFileSet = new HashSet<>(hashBySavedFileMap.keySet());

        hashByUpdateFileMap.keySet().removeIf(updateFileHash -> {
            for (byte[] savedFileHash : hashBySavedFileSet) {
                if (Arrays.equals(updateFileHash, savedFileHash)) {
                    if (hashBySavedFileMap.get(savedFileHash).getSort() != hashByUpdateFileMap.get(updateFileHash).getSort()) {
                        fileNamePostImgMap.get(hashBySavedFileMap.get(savedFileHash).getFileName())
                                          .changeSort(hashByUpdateFileMap.get(updateFileHash).getSort());
                    }
                    hashBySavedFileSet.remove(savedFileHash);
                    return true;
                }
            }
            return false;
        });

        for(byte[] hash : hashBySavedFileSet){
            String postImgFileName = hashBySavedFileMap.get(hash).getFileName();
            post.removePostImgByFileName(postImgFileName);
            fileStore.deleteFile(path + postImgFileName);
            if(hashBySavedFileMap.get(hash).isThumbnail){
                fileStore.deleteFile(path + thumbnailPath + postImgFileName);
            }
        }

        saveImgFile(hashByUpdateFileMap.values().stream().toList(), post);
    }

    private Map<String, Integer> getFileNameAndSortMap(Post post) {
        return post.getPostImgList().stream().collect(
                Collectors.toMap(
                        PostImg::getFileName,
                        PostImg::getSort
                )
        );
    }


    private Map<byte[], ImgFile> getHashByUpdateFileMap(List<ImgFile> imgFileList) {
        return imgFileList.stream().collect(
                Collectors.toMap(
                        imgFile -> fileStore.getHash(Base64.decodeBase64(imgFile.getImg())),
                        imgFile->imgFile
                )
        );
    }

    private Map<byte[], ImgFileName> getHashBySavedFileMap(String path, Post post) {
        return post.getPostImgList().stream().collect(
                Collectors.toMap(
                        pi->fileStore.getHash(path + pi.getFileName()),
                        pi-> new ImgFileName(pi.getFileName(), pi.getSort(), pi.isThumbnail())
                )
        );
    }

    private static Map<String, Boolean> getFileNameAndIsThumbnailMap(Post post) {
        return post.getPostImgList().stream().collect(
                Collectors.toMap(
                        PostImg::getFileName,
                        PostImg::isThumbnail
                )
        );
    }

    @Override
    public Map<Long, List<ImageToString>> getPostThumbnailListByPostList(List<Post> postList) {
        return postList.stream().collect(
                Collectors.toMap(
                        Post::getId,
                        this::getPostThumbnailFileToStringList
                )
        );
    }

    @Override
    public List<ImageToString> getPostImageList(Long postId, List<String> imgUriList) {
        String path = getPostPath(postId);
        return imgUriList.stream().map(
                imgUri -> ImageToString.builder()
                                       .img(fileStore.findFileToEncode(path+imgUri))
                                       .imageType(getImageType(imgUri)).build()
                        ).toList();
    }

    @Override
    public void deleteAllById(Long postId) {
        String path = getPostPath(postId);
        List<PostImg> postImgList = postImgRepository.findAllByPostId(postId);
        postImgList.forEach(
                postImg -> {
                    fileStore.deleteFile(path+postImg.getFileName());
                    if(postImg.isThumbnail())
                     fileStore.deleteFile(path+thumbnailPath+postImg.getFileName());
                }

        );
    }

    @Override
    public ImageToString getPostThumbnailByFilename(String fileName) {
        String path = postPath + thumbnailPath + fileName;
        return ImageToString.builder()
                            .img(fileStore.findFileToEncode(fileName))
                            .imageType(getImageType(fileName))
                            .build();
    }


    private List<ImageToString> getPostThumbnailFileToStringList(Post post) {
        String path = getPostPath(post.getId());
        return post.getPostImgList().stream().filter(PostImg::isThumbnail)
                       .map(
                               postImg -> ImageToString.builder()
                                       .img(fileStore.findFileToEncode(path + thumbnailPath + postImg.getFileName()))
                                       .imageType(getImageType(postImg.getFileName()))
                                       .build()
                       ).toList();

    }

    private ImageType getImageType(String fileName) {
        return ImageType.byString(fileStore.getExtension(fileName));
    }

    private String getPostPath(Long postId) {
        return postPath + postId + "/";
    }

    @Override
    public String getPostThumbnailUrl(Long postId, String imageFileName){
        return imgServerUrl+getPostPath(postId)+thumbnailPath+imageFileName;
    }
    @Override
    public List<String> getPostThumbnailUrlList(Long postId, List<String> imageFileNameList) {
        return imageFileNameList.stream().map(image->getPostThumbnailUrl(postId, image)).toList();
    }

    @Override
    public String getPostImageUrl(Long postId, String imageFileName){
        return imgServerUrl + getPostPath(postId)+imageFileName;
    }

    @Override
    public List<String> getPostImageListUrl(Long postId, List<String> imageFileNameList){
        return imageFileNameList.stream().map(image-> getPostImageUrl(postId, image)).toList();
    }
}

/**
 * 이전 update 코드
 */

/*
        hashBySavedFileMap.keySet().forEach(
                hashBySavedFile -> {
                    *//*for(byte[] updateFile : hashByUpdateFileMap.keySet()){*//*
                    byte[] updateFile = null;
                    boolean isExist = false;
                    for (Iterator<byte[]> itr = hashByUpdateFileMap.keySet().iterator(); itr.hasNext(); ) {
                        updateFile = itr.next();
                        if (Arrays.equals(updateFile, hashBySavedFile)) {
                            log.info("delete complete");
                            itr.remove();
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        log.info("what?");
                        String postImgFileName = hashBySavedFileMap.get(hashBySavedFile);
                        post.removePostImgById(postImgFileName);
                        fileStore.deleteFile(path + postImgFileName);
                    }


                    *//*if(hashByUpdateFileMap.keySet().stream().anyMatch(updateFile -> Arrays.equals(updateFile, hashBySavedFile))) {
                        log.info("delete complete");
                        hashByUpdateFileMap.remove(hashBySavedFile);
                    }else {
                        String postImgFileName = hashBySavedFileMap.get(hashBySavedFile);
                        post.removePostImgById(postImgFileName);
                        fileStore.deleteFile(path+postImgFileName);
                        *//**//**
 * 섬네일도 지워야함
 *//**//*
                    }*//*
                    });
*/