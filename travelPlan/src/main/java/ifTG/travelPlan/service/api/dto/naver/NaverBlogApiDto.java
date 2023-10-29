package ifTG.travelPlan.service.api.dto.naver;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@ToString
public class NaverBlogApiDto {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBlogApiItem> items = new ArrayList<>();

}
