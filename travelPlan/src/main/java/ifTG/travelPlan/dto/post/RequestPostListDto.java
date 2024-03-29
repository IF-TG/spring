package ifTG.travelPlan.dto.post;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.dto.post.enums.*;
import ifTG.travelPlan.exception.CustomErrorException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Data
@Slf4j
public class RequestPostListDto {
    private final Pageable pageable;
    private final OrderMethod orderMethod;
    private final MainCategory mainCategory;
    private Enum<?> subCategory;

    @Builder
    public RequestPostListDto(int page, int perPage, OrderMethod orderMethod, MainCategory mainCategory, String subCategory) throws IllegalAccessException {
        isNullCategory(orderMethod, mainCategory);
        this.pageable = PageRequest.of(page, perPage);
        this.orderMethod = orderMethod;
        this.mainCategory = mainCategory;
        getSubCategory(mainCategory, subCategory);
    }
    private static void isNullCategory(OrderMethod orderMethod, MainCategory mainCategory) throws IllegalAccessException {
        Optional.ofNullable(orderMethod)
                .orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
        Optional.ofNullable(mainCategory)
                .orElseThrow(()->new CustomErrorException(StatusCode.NOT_FOUND_CONTENT));
    }
    private void getSubCategory(MainCategory mainCategory, String subCategory) {
        switch(mainCategory){
            case ORIGINAL -> this.subCategory = null;
            case SEASON -> getSeasonSubcategory(subCategory);
            case THEME -> getThemeSubcategory(subCategory);
            case REGION -> getRegionSubcategory(subCategory);
            case COMPANION -> getCompanionSubCategory(subCategory);
            default -> throw new RuntimeException("잘못된 요청 > MainCategory");
        }
    }
    private void getCompanionSubCategory(String subCategory){
        for(Companions c : Companions.values()){
            if(c.toString().equals(subCategory)){
                this.subCategory = c;
            }
        }
        isNullSubCategory();
    }
    private void getRegionSubcategory(String subCategory){
        for(Regions r : Regions.values()){
            if(r.toString().equals(subCategory)){
                this.subCategory = r;
            }
        }
        isNullSubCategory();
    }
    private void getThemeSubcategory(String subCategory){
        for(Themes t : Themes.values()){
            if(t.toString().equals(subCategory)){
                this.subCategory = t;
            }
        }
        isNullSubCategory();
    }
    private void getSeasonSubcategory(String subCategory) {
        for(Seasons s : Seasons.values()){
            if(s.toString().equals(subCategory)){
                this.subCategory = s;
            }
        }
        isNullSubCategory();
    }
    private void isNullSubCategory() {
        Optional.ofNullable(this.subCategory)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subcategory selection"));
    }
}
