package ifTG.travelPlan.domain.travel;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "destination_img")
public class DestinationImg {
    @Id @Column(name = "destination_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    private String imgUrl;

    private String thumbnailImg;

}
