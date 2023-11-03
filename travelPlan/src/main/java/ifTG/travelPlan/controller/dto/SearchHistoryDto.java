package ifTG.travelPlan.controller.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor
public class SearchHistoryDto {
    private String history;
    private String createAt;


    public SearchHistoryDto(String history, LocalDateTime createAt) {
        this.history = history;
        this.createAt = createAt.format(DateTimeFormatter.ofPattern("yy:MM:dd"));
    }
}
