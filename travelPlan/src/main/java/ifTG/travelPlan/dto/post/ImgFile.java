package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import lombok.Getter;

@Getter

public class ImgFile {
    private final ImageToString image;

    private final boolean isThumbnail;

    public ImgFile(String img, ImageType imageType, boolean isThumbnail) {
        this.image = new ImageToString(img, imageType);
        this.isThumbnail = isThumbnail;
    }

    public String getImg() {
        return image.getImg();
    }

    public ImageType getImageType() {
        return image.getImageType();
    }
}
