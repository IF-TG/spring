package ifTG.travelPlan.domain.user;

import ifTG.travelPlan.dto.user.ScrapType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ScrapFolderImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ScrapType scrapType;

    private final String fileName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "scrapFolder_id")
    private ScrapFolder scrapFolder;

    public ScrapFolderImg(ScrapType scrapType, String fileName, ScrapFolder scrapFolder) {
        this.scrapType = scrapType;
        this.fileName = fileName;
        this.scrapFolder = scrapFolder;
    }
}
