package ifTG.travelPlan.service.api.dto.tourapi.detailintro.sightseeing;

import ifTG.travelPlan.service.api.dto.tourapi.detailintro.DetailIntroDto;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class SightSeeingDetailIntroItem {
    private String contentid;
    private String contenttypeid;
    private String heritage1;
    private String heritage2;
    private String heritage3;
    private String infocenter;
    private String accomcount;
    private String opendate;
    private String restdate;
    private String chkbabycarriage;
    private String chkcreditcard;
    private String chkpet;
    private String expagerange;
    private String expguide;
    private String parking;
    private String useseason;
    private String usetime;
}
