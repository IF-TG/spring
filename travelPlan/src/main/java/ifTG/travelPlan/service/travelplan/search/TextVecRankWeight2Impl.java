package ifTG.travelPlan.service.travelplan.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service

@Slf4j
public class TextRankWeightImpl extends Word2Vec2VImpl implements TextRankWeight{
    @Autowired
    public TextRankWeightImpl(Morpheme morpheme) {
        super(morpheme);
    }

    @Override
    public double getScore(String s1, String s2){
        log.info("getScoreV1 = {}, {}", s1, s2);
        int oneHotInput = morpheme.getIdx(s1);
        int oneHotOutput = morpheme.getIdx(s2);
        return forwardPassWithSoftmax(oneHotInput)[oneHotOutput];
    }
}
