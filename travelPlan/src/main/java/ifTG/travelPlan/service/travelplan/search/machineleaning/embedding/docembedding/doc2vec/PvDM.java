package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.docembedding.doc2vec;

import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.InitArray;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("PvDM")
@RequiredArgsConstructor
public class PvDM implements Doc2Vec{
    private final InitArray initArray;
    private final Morpheme morpheme;

    @Override
    public WeightBuilder learningWeight(LearningBuilder builder) {
        return null;
    }

    @Override
    public double[] forwardPassWithSoftmax(WeightBuilder weightBuilder, int oneHotInput) {
        return new double[0];
    }
}
