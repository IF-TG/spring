package ifTG.travelPlan.domain.diary;

import ifTG.travelPlan.domain.travel.TravelPlan;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Getter
@Table(name = "diaries")
public class Diary {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    //양방향 매핑
    @OneToMany(mappedBy = "diary", cascade = CascadeType.MERGE, orphanRemoval = true)
    private final List<DiaryImg> diaryImgList = new ArrayList<>();

}
