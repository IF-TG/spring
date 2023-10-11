package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.service.api.dto.ContentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * api 요구사항에 따라 변경 예정
 */
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "destinations")
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
    private String overview;
    private String tel;

    /**
     * 양방향 매핑
     */
    @OneToMany(mappedBy = "destination", cascade = CascadeType.REMOVE)
    private List<DestinationImg> destinationImgList = new ArrayList<>();

    @Builder
    public Destination(Long id, Long tourApiContentId, ContentType contentType, String address, String addressDetail, String thumbNail, String title, Integer areaCode, Double mapX, Double mapY, Integer mLevel, String overview, String tel) {
        this.id = id;
        this.tourApiContentId = tourApiContentId;
        this.contentType = contentType;
        this.address = address;
        this.addressDetail = addressDetail;
        this.thumbNail = thumbNail;
        this.title = title;
        this.areaCode = areaCode;
        this.mapX = mapX;
        this.mapY = mapY;
        this.mLevel = mLevel;
        this.overview = overview;
        this.tel = tel;
    }
}
