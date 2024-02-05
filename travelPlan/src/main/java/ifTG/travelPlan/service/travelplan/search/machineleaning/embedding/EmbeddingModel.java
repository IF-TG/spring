package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;

public interface EmbeddingModel {

    WeightBuilder learningWeight(LearningBuilder builder);
    double[] forwardPassWithSoftmax(WeightBuilder weightBuilder, int oneHotInput);
}
