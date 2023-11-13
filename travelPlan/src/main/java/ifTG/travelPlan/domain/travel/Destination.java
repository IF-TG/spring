package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.domain.travel.destinationdetail.*;
import ifTG.travelPlan.dto.travel.enums.Category;
import ifTG.travelPlan.service.api.dto.ContentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * api 요구사항에 따라 변경 예정
 */
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "destinations")
@ToString
public class Destination{
    @Id @Column(name = "destination_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long tourApiContentId;
    @Enumerated(value = EnumType.STRING)
    private ContentType contentType;
    @Column(nullable = false)
    private String address;
    private String addressDetail;
    private String thumbNail;
    @Column(nullable = false)
    private String title;
    private Integer areaCode;
    private Double mapX;
    private Double mapY;
    private Integer mLevel;
    private String zipcode;
    @Embedded
    private Category category;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String overview;
    private String tel;

    /**
     * 양방향 매핑
     */
    @OneToMany(mappedBy = "destination", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<DestinationImg> destinationImgList = new ArrayList<>();

    /**
     * one to one 은 양방향 매핑시 lazy 어려움
     */
    @Builder
    public Destination(Long tourApiContentId, ContentType contentType, String zipcode, String address, String addressDetail, String thumbNail,
                       String title, Integer areaCode, Double mapX, Double mapY, Integer mLevel, String tel, Category category) {
        this.tourApiContentId = tourApiContentId;
        this.contentType = contentType;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
        this.thumbNail = thumbNail;
        this.title = title;
        this.areaCode = areaCode;
        this.mapX = mapX;
        this.mapY = mapY;
        this.mLevel = mLevel;
        this.tel = tel;
        this.category =category;
    }

    public void insertOverViewAtTourApiDetailCommon(String overview){
        this.overview = overview;
    }
}
