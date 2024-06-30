package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.dto.ImageToString;
import ifTG.travelPlan.dto.ImageType;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImgFile {
    @Hidden
    private ImageToString image;
    private boolean isThumbnail;
    private int sort;

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
