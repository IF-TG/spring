package ifTG.travelPlan.dto.travel.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public enum LargeCategory {
    A01("자연"),
    A02("인문(문화/예술/역사)"),
    A03("레포츠"),
    A04("쇼핑"),
    A05("음식"),
    B02("숙박"),
    C01("추천코스");

    private final String value;
    LargeCategory(String value){
        this.value = value;
    }

    public static LargeCategory findByCode(String code){
        for (LargeCategory l: LargeCategory.values()){
            if (l.name().equals(code)){
                return l;
            }
        }
        throw new IllegalArgumentException("No enum found with code: " + code);
    }
}
