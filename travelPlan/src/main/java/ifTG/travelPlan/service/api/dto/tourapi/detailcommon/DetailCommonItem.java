package ifTG.travelPlan.service.api.dto.tourapi.detailcommon;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DetailCommonItem {
    private int contentid;
    private int contenttypeid;
    private String title;
    private int booktour;
    private String createdtime;
    private String hmpg;
    private String modifiedtime;
    private String tel;
    private String telname;
    private String firstImage;
    private String firstImage2;
    private String cpyrhtDivCd;
    private int areacode;
    private int sigungucode;
    private String cat1;
    private String cat2;
    private String cat3;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String overview;
}
