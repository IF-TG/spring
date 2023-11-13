package ifTG.travelPlan.service.travelplan.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("TextRankWeightV2Impl")
@Primary
@Slf4j
public class TextRankWeightV2Impl extends Word2Vec2VImpl implements TextRankWeight{

    @Autowired
    public TextRankWeightV2Impl(Morpheme morpheme) {
        super(morpheme);
    }

    /**
     * 코사인 유사도 버전
     */
    @Override
    public double getScore(String s1, String s2){
        log.info("getScoreV2 = {}, {}", s1, s2);
        int idxA = morpheme.getIdx(s1);
        int idxB = morpheme.getIdx(s2);
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i<DIMENSION; i++){
            dotProduct += inputHiddenWeight[i][idxA]*inputHiddenWeight[i][idxB];
            normA += inputHiddenWeight[i][idxA]*inputHiddenWeight[i][idxA];
            normB += inputHiddenWeight[i][idxB]*inputHiddenWeight[i][idxB];
        }

        return dotProduct/(Math.sqrt(normA)*Math.sqrt(normB));
    }
}
