package ifTG.travelPlan.domain.travel;

import ifTG.travelPlan.service.api.dto.CatDto;
import ifTG.travelPlan.service.api.dto.ContentType;
import ifTG.travelPlan.service.api.dto.MapXY;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
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

    private String title;

    private String address;

    private String addressDetail;

    private Integer areaCode;

    @Column(unique = true)
    private Long tourApiContentId;

    @Enumerated(value = EnumType.STRING)
    private ContentType contentType;

    private Double mapX;

    private Double mapY;

    private Integer mLevel;

    private String info;

    private String petAccommodationAllowed;

    private String petCompanionRequirements;

    /**
     * 양방향 매핑
     */
    @OneToMany(mappedBy = "destination", cascade = CascadeType.REMOVE)
    private List<DestinationImg> destinationImgList = new ArrayList<>();


}
