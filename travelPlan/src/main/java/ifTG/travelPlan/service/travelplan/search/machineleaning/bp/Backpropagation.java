package ifTG.travelPlan.service.travelplan.search.machineleaning.bp;

public interface Backpropagation {
    double[] forwardPassWithSoftmaxForOneHotEncoding(double[][] inputHiddenWeight, double[][] hiddenOutputWeight, int oneHotInput);
    void learnForOneHotEncodingForSoftmax(double[][] inputHiddenWeight, double[][] hiddenOutputWeight, Double learnRate, double[] result, int oneHotInput, int oneHotOutput);
}
