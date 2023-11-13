package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;

@Getter
public class ImgFile {
    @Hidden
    private final ImageToString image;
    private final boolean isThumbnail;
    private final int sort;

    public ImgFile(String img, ImageType imageType, boolean isThumbnail, int sort) {
        this.image = new ImageToString(img, imageType);
        this.isThumbnail = isThumbnail;
        this.sort = sort;
    }

    public String getImg() {
        return image.getImg();
    }

    public ImageType getImageType() {
        return image.getImageType();
    }
}
