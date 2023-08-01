package ifTG.travelPlan.domain.travel.destinationroute;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

/**
 * api 요구사항에 따라 변경 예정
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "destinations")
public class Destination{
    @Id @Column(name = "destination_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinationName;
    private String location;
    @Formula("(SELECT COUNT(1) FROM destination_likes d WHERE d.destinationId = id AND d.type = type)")
    private Integer likes;
    @Column(nullable = false)
    private LocalDateTime eta;

}
