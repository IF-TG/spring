package ifTG.travelPlan.service.filestore;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.post.ImgFile;

import java.util.List;
import java.util.Map;

public interface PostImgFileService {
    void saveImgFile(List<ImgFile> imgFiles, Post post);
    void updateImgFile(List<ImgFile> imgFileList, Post post);
    Map<Long, List<ImageToString>> getPostThumbnailListByPostList(List<Post> postList);
    List<ImageToString> getPostImageList(Long postId, List<String> imgUriList);
    void deleteAllById(Long postId);

    ImageToString getPostThumbnailByFilename(String fileName);
     String getPostThumbnailUrl(Long postId, String imageFileName);
    List<String> getPostThumbnailUrlList(Long id, List<String> toList);

    String getPostImageUrl(Long postId, String imageFileName);

    List<String> getPostImageListUrl(Long postId, List<String> imageFileNameList);
}
