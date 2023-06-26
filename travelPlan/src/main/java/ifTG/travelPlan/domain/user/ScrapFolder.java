package ifTG.travelPlan.domain.user;

import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.travel.DestinationScrap;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Table(name="scrap_folder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScrapFolder {

    @Id
    @Column(name = "scrap_folder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, length = 100)
    private String folderName;

    /**
     * 양방향 매핑
     */
    @OneToMany(mappedBy = "scrapFolder")
    List<DestinationScrap> destinationScrapList;

    @OneToMany(mappedBy = "scrapFolder")
    List<PostScrap> postScrapList;



}
