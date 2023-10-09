package ifTG.travelPlan.service.api.dto.tourapi.detailcommon;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class DetailCommonItem {
    private String contentid;
    private String contenttypeid;
    private String title;
    private String booktour;
    private String createdtime;
    private String hmpg;
    private String modifiedtime;
    private String tel;
    private String telname;
    private String firstimage;
    private String firstimage2;
    private String cpyrhtDivCd;
    private String areacode;
    private String sigungucode;
    private String cat1;
    private String cat2;
    private String cat3;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String overview;
}
