package ifTG.travelPlan.service.travelplan.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

@Slf4j
public class TextVecRankWeight2Impl extends Word2VecV2Impl implements TextRankWeight{
    @Autowired
    public TextVecRankWeight2Impl(Morpheme morpheme) {
        super(morpheme);
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
