package ifTG.travelPlan.service.travelplan.search.machineleaning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("TextRankWeightV2Impl")
@Primary
@Slf4j
public class TextRankWeightV22ImplVec extends Word2VecV2Impl implements TextRankWeight{

    @Autowired
    public TextRankWeightV22ImplVec(Morpheme morpheme) {
        super(morpheme);
    }

    /**
     * 코사인 유사도 버전
     */
    @Override
    public double getScore(String s1, String s2){
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

    @Override
    public double getScore(double[] s1, double[] s2){
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i<100; i++){
            dotProduct += s1[i]*s2[i];
            normA += s1[i]*s1[i];
            normB += s2[i]*s2[i];
        }

        return dotProduct/(Math.sqrt(normA)*Math.sqrt(normB));
    }
}