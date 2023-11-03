package ifTG.travelPlan.dto.travel.enums;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class Category {
    @Enumerated(value = EnumType.STRING)
    private LargeCategory largeCategory;
    @Enumerated(value = EnumType.STRING)
    private MiddleCategory middleCategory;
    @Enumerated(value = EnumType.STRING)
    private SmallCategory smallCategory;
}
