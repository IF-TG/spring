package ifTG.travelPlan.service.travelplan.search.machineleaning.bp;

import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations.ActivationFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Slf4j
public class ShallowNeuralNetwork implements Backpropagation{
    @Override
    public double[] forwardPassVectorWithSoftmax(
            double[] inputVector,
            double[][] hiddenOutputWeight,
            Function<Double, Double> activationFunc
    ){
        double[] result = new double[hiddenOutputWeight[0].length];
        int dimension = inputVector.length;
        int resultSize = result.length;
        for (int i = 0; i<dimension; i++){
            for (int j = 0; j<resultSize; j++){
                result[j] += inputVector[i] * hiddenOutputWeight[i][j];
            }
        }
        return ActivationFunction.softmaxArray().apply(result);
    }

    @Override
    public void learnVectorForOneHotOutputWithSoftmax(
            double[] inputVector,
            double[] learningVector,
            @Nullable Double avg,
            double[][] hiddenOutputWeight,
            Double learnRate,
            double[] result,
            int targetIdx,
            Function<Double, Double> activationFunctionDifferential){
        double[] delta = new double[result.length];

        int resultSize = result.length;
        for (int i =0; i<resultSize; i++){
            if (i == targetIdx){
                delta[i] = result[i]-1;
            }else{
                delta[i] = result[i];
            }
        }
        int dimension = inputVector.length;
        for (int i =0; i<dimension; i++){
            double hiddenDelta = 0;
            for (int j =0; j<resultSize; j++){
                hiddenOutputWeight[i][j]-=learnRate*inputVector[i]*delta[j];
                hiddenDelta += hiddenOutputWeight[i][j]*delta[j];
            }
            hiddenDelta *= activationFunctionDifferential.apply(inputVector[i]);

            learningVector[i] -= learnRate*hiddenDelta/(avg==null?1:avg);
        }
    }

    @Override
    public double[] forwardPassWithSoftmaxForOneHotEncoding(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight,
            int oneHotInput,
            Function<Double, Double> activationFunc) {
        double[] result = forwardPassForOneHotEncoding(inputHiddenWeight, hiddenOutputWeight, oneHotInput, activationFunc);
        return ActivationFunction.softmaxArray().apply(result);
    }

    private static double[] forwardPassForOneHotEncoding(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight,
            int oneHotInput,
            Function<Double, Double> activationFunc) {
        int size = hiddenOutputWeight[0].length;
        int dimension = inputHiddenWeight[0].length;
        double[] result = new double[size];
        for (int i = 0; i<dimension; i++){
            for (int j = 0; j<size; j++){
                result[j] += activationFunc.apply(inputHiddenWeight[oneHotInput][i])*hiddenOutputWeight[i][j];
            }
        }
        return result;
    }

    @Override
    public void learnForOneHotEncodingWithSoftmax(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight,
            Double learnRate,
            double[] result,
            int oneHotInput,
            int oneHotOutput,
            Function<Double, Double> activationFunctionDifferential) {
        double[] delta = new double[result.length];
        for (int i =0; i<result.length; i++){
            if (i == oneHotOutput){
                delta[i] = result[i]-1;
            }else{
                delta[i] = result[i];
            }
        }
        for (int i =0; i<inputHiddenWeight[0].length; i++){
            double hiddenDelta = 0;
            for (int j =0; j<result.length; j++){
                hiddenOutputWeight[i][j]-=learnRate*inputHiddenWeight[oneHotInput][i]*delta[j];
                hiddenDelta += hiddenOutputWeight[i][j]*delta[j];
            }
            hiddenDelta *= activationFunctionDifferential.apply(inputHiddenWeight[oneHotInput][i]);
            inputHiddenWeight[oneHotInput][i] -= learnRate*hiddenDelta;
        }
    }


}
