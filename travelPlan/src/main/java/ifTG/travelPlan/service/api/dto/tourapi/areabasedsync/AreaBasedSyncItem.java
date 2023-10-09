package ifTG.travelPlan.service.api.dto.tourapi.areabasedsync;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AreaBasedSyncItem {
   private String addr1;
   private String addr2;
   private int areaCode;
   private Integer booktour;
   private String cat1;
   private String cat2;
   private String cat3;
   private String contentid;
   private int contenttypeid;
   private String createdtime;
   private String firstimage;
   private String firstimage2;
   private String cpyrhtDivCd;
   private double mapx;
   private double mapy;
   private int mlevel;
   private String modifiedtime;
   private int showflag;
   private int sigungucode;
   private String tel;
   private String title;
   private int zipcode;
}
