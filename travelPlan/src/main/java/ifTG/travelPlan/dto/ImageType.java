package ifTG.travelPlan.dto;

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
        throw new IllegalArgumentException("No matching FileType for value: " + value);
    }
}
