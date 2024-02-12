package ifTG.travelPlan.service.travelplan.search.machineleaning.bp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@Slf4j
public class ShallowNeuralNetwork implements Backpropagation{
    @Override
    public double[] forwardPassWithSoftmaxForOneHotEncoding(double[][] inputHiddenWeight, double[][] hiddenOutputWeight, int oneHotInput, Function<Double, Double> func) {
        double[] result = forwardPassForOneHotEncoding(inputHiddenWeight, hiddenOutputWeight, oneHotInput);
        return softmax(result);
    }

    private static double[] forwardPassForOneHotEncoding(double[][] inputHiddenWeight, double[][] hiddenOutputWeight, int oneHotInput) {
        int size = hiddenOutputWeight[0].length;
        int dimension = inputHiddenWeight[0].length;
        double[] result = new double[size];
        for (int i = 0; i<dimension; i++){
            for (int j = 0; j<size; j++){
                result[j] += inputHiddenWeight[oneHotInput][i]* hiddenOutputWeight[i][j];
            }
        }
        return result;
    }

    private static double[] softmax(double[] result) {
        double[] softmax = new double[result.length];
        double max = getMax(result);
        double exp = getExp(result, max);
        for (int i = 0; i< result.length; i++){
            softmax[i] = Math.exp(result[i]-max)/exp;
        }
        return softmax;
    }





    @Override
    public void learnForOneHotEncodingForSoftmax(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight,
            Double learnRate,
            double[] result,
            int oneHotInput,
            int oneHotOutput) {
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
            inputHiddenWeight[oneHotInput][i] -= learnRate*hiddenDelta;
        }
    }
}
