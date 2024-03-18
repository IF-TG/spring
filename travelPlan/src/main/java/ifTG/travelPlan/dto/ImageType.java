package ifTG.travelPlan.dto;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.exception.CustomErrorException;

public enum ImageType {
    jpg("jpg"),
    jpeg("jpeg"),
    png("png");
    private final String value;

    ImageType(String value){
        this.value = value;
    }

    public static ImageType byString(String value) {
        for (ImageType imageType : values()) {
            if (imageType.value.equalsIgnoreCase(value)) {
                return imageType;
            }
        }
        throw new CustomErrorException(StatusCode.INVALID_IMAGE_TYPE);
    }
}
