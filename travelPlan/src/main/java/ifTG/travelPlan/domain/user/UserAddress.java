package ifTG.travelPlan.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Getter
@Table(name = "user_addresses")
@NoArgsConstructor
public class UserAddress {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String sido;
    @Column(length = 20, nullable = false)
    private String sigungu;

    @Column(length = 20, nullable = false)
    private String eupmyendong;

    @Column(length = 50, nullable = false)
    private String roadName;

    @Column(length = 20, nullable = false)
    private String buildingNumber;

    @Column(length = 10, nullable = false)
    private String zipCode;

    @Column(length = 50, nullable = false)
    private String subBuildingNumber;

    /**
     * 양방향 매핑
     */
    @OneToOne(mappedBy = "userAddress", fetch = LAZY, cascade = CascadeType.ALL)
    private User user;

    /**
     * 생성자
     */
    @Builder
    public UserAddress(String sido, String sigungu, String eupmyendong, String roadName, String buildingNumber, String zipCode, String subBuildingNumber) {
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmyendong = eupmyendong;
        this.roadName = roadName;
        this.buildingNumber = buildingNumber;
        this.zipCode = zipCode;
        this.subBuildingNumber = subBuildingNumber;
    }

    /**
     * 1차 캐싱 npe 방지를 위한 함수
     */
    public void addUser(User user) {
        this.user = user;
    }
}
