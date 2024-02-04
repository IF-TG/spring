package ifTG.travelPlan.service.travelplan.search.machineleaning;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 더 이상 쓰지 않음
 */
@Service
@Deprecated
@Slf4j
<<<<<<< HEAD:travelPlan/src/main/java/ifTG/travelPlan/service/travelplan/search/machineleaning/TextVecRankWeight2.java
public class TextVecRankWeight2 extends DestinationOverViewVectorV2 implements TextRankWeight{
    @Autowired
    public TextVecRankWeight2(Morpheme morpheme) {
        super(morpheme);
=======
public class TextRankWeightV1Impl extends DestinationOverViewVectorV2 implements TextRankWeight{

    @Autowired
    public TextRankWeightV1Impl(DestinationOverviewNounExtractor de, EmbeddingModel em, Morpheme morpheme) {
        super(de, em, morpheme);
>>>>>>> d44af60ff76f20667a6a68c636650c12d1b637a9:travelPlan/src/main/java/ifTG/travelPlan/service/travelplan/search/machineleaning/TextRankWeightV1Impl.java
    }

    @Override
    public double getScore(double[] s1, double[] s2) {
        return 0;
    }

    @Override
    public double getScore(String s1, String s2){
        log.info("getScoreV1 = {}, {}", s1, s2);
        int oneHotInput = morpheme.getIdx(s1);
        int oneHotOutput = morpheme.getIdx(s2);
        return forwardPassWithSoftmax(oneHotInput)[oneHotOutput];
    }
}
