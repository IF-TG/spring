package ifTG.travelPlan.service.filestore;


import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostImg;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import ifTG.travelPlan.dto.post.ImgFile;
import ifTG.travelPlan.repository.springdata.post.PostImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private final FileStore fileStore;
    private final PostImgRepository postImgRepository;

    @Override
    public void saveImgFile(List<ImgFile> imgFiles, Post post) {
        String postIdUri = getPostPath(post.getId());
        imgFiles.forEach(imgFile -> {
            String savedFileName = saveImgFileToFileStore(postIdUri, imgFile);
            postImgRepository.save(new PostImg(savedFileName, post, imgFile.isThumbnail()));
        });
    }

    private String saveImgFileToFileStore(String postIdUri, ImgFile imgFile) {
        String savedFileName = fileStore.saveFileToBase64Decode(imgFile.getImg(), postIdUri, imgFile.getImageType().toString());

        if(imgFile.isThumbnail()){
            new Thread(()->fileStore.createThumbnailAndSaveFile(postIdUri, savedFileName, thumbnailPath)).start();
        }
        return savedFileName;
    }

    @Override
    public void updateImgFile(List<ImgFile> imgFileList, Post post) {
        String path = getPostPath(post.getId());
        List<String> fileNameList = post.getPostImgList().stream().map(PostImg::getFileName).toList();

        Map<String, Boolean> isThumbnailMap = getFileNameAndIsThumbnailMap(post);
        Map<byte[], String> hashBySavedFileMap = getHashBySavedFileMap(path, fileNameList);
        Map<byte[], ImgFile> hashByUpdateFileMap = getHashByUpdateFileMap(imgFileList);
        /**
         *java.util.ConcurrentModificationException 문제 (해결)
         */

        HashSet<byte[]> hashByUpdateFileSet = new HashSet<>(hashByUpdateFileMap.keySet());
        HashSet<byte[]> hashBySavedFileSet = new HashSet<>(hashBySavedFileMap.keySet());
        retainAllHashByUpdateFileMap(hashByUpdateFileMap, hashByUpdateFileSet, hashBySavedFileSet);

        for(byte[] hash : hashBySavedFileSet){
            String postImgFileName = hashBySavedFileMap.get(hash);
            post.removePostImgById(postImgFileName);
            fileStore.deleteFile(path + postImgFileName);
            if(isThumbnailMap.get(postImgFileName)){
                fileStore.deleteFile(path + thumbnailPath + postImgFileName);
            }
        }

        saveImgFile(hashByUpdateFileMap.values().stream().toList(), post);
    }

    private static void retainAllHashByUpdateFileMap(Map<byte[], ImgFile> hashByUpdateFileMap, HashSet<byte[]> hashByUpdateFileSet, HashSet<byte[]> hashBySavedFileSet) {
        hashByUpdateFileSet.retainAll(hashBySavedFileSet);
        for(byte[] hash : hashByUpdateFileSet){
            hashByUpdateFileMap.remove(hash);
        }
    }

    private Map<byte[], ImgFile> getHashByUpdateFileMap(List<ImgFile> imgFileList) {
        return imgFileList.stream().collect(
                Collectors.toMap(
                        imgFile -> fileStore.getHash(Base64.decodeBase64(imgFile.getImg())),
                        imgFile->imgFile
                )
        );
    }

    private Map<byte[], String> getHashBySavedFileMap(String path, List<String> fileNameList) {
        return fileNameList.stream().collect(
                Collectors.toMap(
                        fileName->fileStore.getHash(path + fileName),
                        fileName->fileName
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
        return postPath + postId + "\\";
    }
    public String getPostThumbnailUrl(Long postId, String imageFileName){
        return imgServerUrl + postId + thumbnailPath + imageFileName;
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