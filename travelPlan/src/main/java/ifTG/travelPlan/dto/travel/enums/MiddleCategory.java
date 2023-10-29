<<<<<<< HEAD
package ifTG.travelPlan.dto.travel.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MiddleCategory {
    A0101("자연관광지"),
    A0102("관광자원"),
    A0201("역사관광지"),
    A0202("휴양관광지"),
    A0203("체험관광지"),
    A0204("산업관광지"),
    A0205("건축/조형물"),
    A0206("문화시설"),
    A0207("축제"),
    A0208("공연/행사"),
    A0301("레포츠"),
    A0302("육상"),
    A0303("수상"),
    A0304("항공"),
    A0305("복합"),
    A0401("쇼핑"),
    A0502("음식점");

    private final String value;

    MiddleCategory(String value){
        this.value =value;
    }

    public static MiddleCategory findByCode(String code){
        for (MiddleCategory m: MiddleCategory.values()){
            if (m.name().equals(code)){
                return m;
            }
        }
        throw new IllegalArgumentException("No enum found with code: " + code);
    }
=======
package ifTG.travelPlan.dto.travel.enums;public class MiddleCategory {
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
}
