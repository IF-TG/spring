package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.DestinationWordVectorV2;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * 더 이상 쓰지 않음
 */
@Component
@Deprecated
@Slf4j
public class TextRankWeightV1Impl extends DestinationWordVectorV2 implements TextRankWeight {
    private final EmbeddingModel em;

    @Autowired
    public TextRankWeightV1Impl(@Qualifier("skipGram") EmbeddingModel em, DestinationOverviewNounExtractor de) {
        super(de);
        this.em = em;
    }

    public double[] forwardPassWithSoftmax(int idx){
        return em.forwardPassWithSoftmax(
                WeightBuilder.builder()
                        .inputHiddenWeight(inputHiddenWeight)
                        .hiddenOutputWeight(hiddenOutputWeight).build(), idx);
    }



    @Override
    public double getScore(String s1, String s2){
        log.info("getScoreV1 = {}, {}", s1, s2);
        int oneHotInput = de.getIdx(s1);
        int oneHotOutput = de.getIdx(s2);
        return forwardPassWithSoftmax(oneHotInput)[oneHotOutput];
    }


    @Override
    public double getScore(double[] s1, double[] s2) {
        return 0;
    }
}
