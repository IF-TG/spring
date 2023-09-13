package ifTG.travelPlan.service.filestore;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.user.ScrapFolder;
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
}
