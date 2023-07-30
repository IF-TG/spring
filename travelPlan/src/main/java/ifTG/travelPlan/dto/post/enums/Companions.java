package ifTG.travelPlan.dto.post.enums;

public enum Companions {
    ALONE("혼자"),
    FAMILY("가족"),
    PARENTS("부모님"),
    WITH_CHILDREN("아이들과"),
    PARTNER("애인"),
    FRIEND("친구"),
    PET("애완동물");

    private final String value;

    Companions(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }
}
