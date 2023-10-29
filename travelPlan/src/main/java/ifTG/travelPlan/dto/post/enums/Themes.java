package ifTG.travelPlan.dto.post.enums;

public enum Themes {
    REST("휴식"),
    SHOPPING("쇼핑"),
    CAMPING_GLAMPING("캠핑/글램핑"),
    ADVENTURE("모험"),
    LOCAL_EXPERIENCE("현지 체험"),
    FESTIVAL("축제");

    private String value;
    Themes(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }
}
