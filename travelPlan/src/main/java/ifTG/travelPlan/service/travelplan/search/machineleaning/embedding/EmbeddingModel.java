package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

public interface EmbeddingModel {

    WeightBuilder learningWeight(LearningBuilder builder);
    double[] forwardPassWithSoftmax(WeightBuilder weightBuilder, int oneHotInput);
}
