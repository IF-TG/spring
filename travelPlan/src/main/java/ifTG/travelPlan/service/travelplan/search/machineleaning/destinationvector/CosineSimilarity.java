package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.DestinationWordVectorV2;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.vector.VectorCalculating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("TextRankWeightV2Impl")
@Primary
@Slf4j
public class CosineSimilarity extends DestinationWordVectorV2 implements TextRankWeight {
    @Autowired
    public CosineSimilarity(DestinationOverviewNounExtractor de) {
        super(de);
    }

    /**
     * 코사인 유사도 버전
     */
    @Override
    public double getScore(String s1, String s2){
        int idxA = de.getIdx(s1);
        int idxB = de.getIdx(s2);
        return VectorCalculating.cosineSimilarity(inputHiddenWeight[idxA], inputHiddenWeight[idxB]);
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
