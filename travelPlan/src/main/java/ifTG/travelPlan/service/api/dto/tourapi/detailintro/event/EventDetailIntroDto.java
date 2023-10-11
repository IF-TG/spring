package ifTG.travelPlan.service.api.dto.tourapi.detailintro.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class EventDetailIntroDto {
    private List<EventDetailIntroItem> item = new ArrayList<>();
}
