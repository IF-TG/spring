package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 더 이상 쓰지 않음
 */
@Component
@Deprecated
@Slf4j
public class TextRankWeightV1Impl extends DestinationOverViewVectorV2 implements TextRankWeight {

    @Autowired
    public TextRankWeightV1Impl(DestinationOverviewNounExtractor de, @Qualifier("skipGram") EmbeddingModel em) {
        super(de, em);
    }

    @Override
    public double getScore(double[] s1, double[] s2) {
        return 0;
    }

    @Override
    public double getScore(String s1, String s2){
        log.info("getScoreV1 = {}, {}", s1, s2);
        int oneHotInput = de.getIdx(s1);
        int oneHotOutput = de.getIdx(s2);
        return forwardPassWithSoftmax(oneHotInput)[oneHotOutput];
    }
}
