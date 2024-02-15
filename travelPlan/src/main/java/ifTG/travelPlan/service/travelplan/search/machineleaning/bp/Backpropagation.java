package ifTG.travelPlan.service.travelplan.search.machineleaning.bp;

import org.springframework.lang.Nullable;

import java.util.function.Function;

public interface Backpropagation {
    double[] forwardPassVectorWithSoftmax(
            double[] inputVector,
            double[][] hiddenOutputWeight,
            Function<Double, Double> activationFunc
    );

    void learnVectorForOneHotOutputWithSoftmax(
            double[] inputVector,
            double[] learningVector,
            @Nullable Double avg,
            double[][] hiddenOutputWeight,
            Double learnRate,
            double[] result,
            int targetIdx,
            Function<Double, Double> activationFunctionDifferential);

    double[] forwardPassWithSoftmaxForOneHotEncoding(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight,
            int oneHotInput,
            Function<Double, Double> activationFunc);

    void learnForOneHotEncodingWithSoftmax(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight,
            Double learnRate,
            double[] result,
            int oneHotInput,
            int oneHotOutput,
            Function<Double, Double> activationFunctionDifferential
    );
}
