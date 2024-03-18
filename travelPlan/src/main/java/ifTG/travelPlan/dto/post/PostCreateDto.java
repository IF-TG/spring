package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.dto.post.enums.Companions;
import ifTG.travelPlan.dto.post.enums.Regions;
import ifTG.travelPlan.dto.post.enums.Seasons;
import ifTG.travelPlan.dto.post.enums.Themes;
import ifTG.travelPlan.exception.CustomErrorException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Slf4j
public class PostCreateDto {
    private final String title;
    private final String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private final List<Themes> themes;
    private final List<Regions> regions;
    private final List<Seasons> seasons = new ArrayList<>();
    private final List<Companions> companions;
    private final List<ImgFile> imgFileList;
    private final Double mapX;
    private final Double mapY;
    @Builder
    public PostCreateDto(String title, String content, String startDate, String endDate,
                         List<Themes> themes, List<Regions> regions, List<Companions> companions, List<ImgFile> imgFileList,
                         Double mapX, Double mapY) {
        this.title = title;
        this.content = content;
        putDate(startDate, endDate);
        this.themes = themes != null ? new ArrayList<>(themes) : new ArrayList<>();
        this.regions = regions != null ? new ArrayList<>(regions) : new ArrayList<>();
        this.companions = companions != null ? new ArrayList<>(companions) : new ArrayList<>();
        this.imgFileList = imgFileList != null ? new ArrayList<>(imgFileList) : new ArrayList<>();
        boolean[] isSeason = getIsSeason();
        addSeasons(isSeason);
        this.mapX = mapX;
        this.mapY = mapY;
    }
    private void putDate(String startDate, String endDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        try {
            this.startDate = LocalDate.parse(startDate, dateTimeFormatter);
            this.endDate = LocalDate.parse(endDate, dateTimeFormatter);
        }catch (DateTimeParseException e){
            throw new CustomErrorException(StatusCode.INVALID_DATE_FORMAT);
        }
    }
    private boolean[] getIsSeason() {
        boolean[] isSeason = new boolean[4];
        Period period = Period.between(this.startDate, this.endDate);
        if (1<=period.getYears()){
            Arrays.fill(isSeason, true);
            return isSeason;
        }
        int periodMonths = period.getMonths();
        int startDateMonthValue = this.startDate.getMonthValue();
        for(int i = 0; i<= periodMonths; i++){
            int month = startDateMonthValue+i;
            if(12<month){
                month -= 12;
                isWhatSeason(isSeason, month);
            }else{
                isWhatSeason(isSeason, month);
            }
        }
        return isSeason;
    }
    private static void isWhatSeason(boolean[] isSeason, int month) {
        if(month == 12 || month <= 2){
            isSeason[3]=true;
        }else if(month <=5){
            isSeason[0]=true;
        }else if(month <=8){
            isSeason[1]=true;
        }else if (month <=11){
            isSeason[2]=true;
        }
    }
    private void addSeasons(boolean[] isSeason) {
        if(isSeason[0]){
            seasons.add(Seasons.SPRING);
        }
        if(isSeason[1]){
            seasons.add(Seasons.SUMMER);
        }
        if(isSeason[2]){
            seasons.add(Seasons.AUTUMN);
        }
        if(isSeason[3]){
            seasons.add(Seasons.WINTER);
        }
    }


}
