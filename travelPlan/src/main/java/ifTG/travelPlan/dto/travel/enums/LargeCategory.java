<<<<<<< HEAD
package ifTG.travelPlan.dto.travel.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public enum LargeCategory {
    A01("자연"),
    A02("인문"),
    A03("레포츠"),
    A04("쇼핑"),
    A05("음식");

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
=======
package ifTG.travelPlan.dto.travel.enums;public class LargeCategory {
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
}
