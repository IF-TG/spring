package ifTG.travelPlan.dto.post.enums;

import org.springframework.ui.context.Theme;

public enum Regions {
    SEOUL("서울"),
    GYEONGGI("경기"),
    INCHEON("인천"),
    BUSAN("부산"),
    DAEGU("대구"),
    GWANGJU("경주"),
    DAEJEON("대전"),
    ULSAN("울산"),
    SEJONG("세종"),
    GANGWON("강원도"),
    CHUNGBUK("충청북도"),
    CHUNGNAM("충청남도"),
    JEONBUK("전라북도"),
    JEONNAM("전라남도"),
    GYEONGBUK("경상북도"),
    GYEONGNAM("경상남도"),
    JEJU("제주");

    private final String value;

    Regions(String value) {
        this.value = value;
    }

    public static String getInstance(String regions) {
        for (Regions r : Regions.values()){
            if (r.toString().equals(regions)){
                return r.getValue();
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

}
