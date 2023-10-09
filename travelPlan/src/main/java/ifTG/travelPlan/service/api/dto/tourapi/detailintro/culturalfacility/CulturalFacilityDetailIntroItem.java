package ifTG.travelPlan.service.api.dto.tourapi.detailintro.culturalfacility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class CulturalFacilityDetailIntroItem {
    private String contentid;
    private String contenttypeid;
    private String accomcountculture;
    private String chkbabycarriageculture;
    private String chkcreditcardculture;
    private String chkpetculture;
    private String discountinfo;
    private String infocenterculture;
    private String parkingculture;
    private String parkingfee;
    private String restdateculture;
    private String usefee;
    private String usetimeculture;
    private String scale;
    private String spendtime;
}
