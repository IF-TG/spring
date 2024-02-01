package ifTG.travelPlan.service.travelplan.search.machineleaning.bp;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BackpropagationImpl implements Backpropagation{
    @Override
    public double[] forwardPassWithSoftmaxForOneHotEncoding(double[][] inputHiddenWeight, double[][] hiddenOutputWeight, int oneHotInput) {
        double[] result = forwardPass(inputHiddenWeight, hiddenOutputWeight, oneHotInput);
        return softmax(result);
    }

    private static double[] forwardPass(double[][] inputHiddenWeight, double[][] hiddenOutputWeight, int oneHotInput) {
        int size = hiddenOutputWeight[0].length;
        double[] result = new double[size];
        for (int i = 0; i< inputHiddenWeight.length; i++){
            for (int j = 0; j<size; j++){
                result[j] += inputHiddenWeight[i][oneHotInput]* hiddenOutputWeight[i][j];
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

    private static double getExp(double[] result, double max) {
        double tmp = 0;
        for (double v : result) {
            tmp += Math.exp(v- max);
        }
        return tmp;
    }

    private static double getMax(double[] result) {
        double max = 0;
        for (double v : result){
            if (max<v){
                max = v;
            }
        }
        return max;
    }

    @Override
    public void learnForOneHotEncoding(
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
        for (int i =0; i<inputHiddenWeight.length; i++){
            double hiddenDelta = 0;
            for (int j =0; j<result.length; j++){
                hiddenOutputWeight[i][j]-=learnRate*inputHiddenWeight[i][oneHotInput]*delta[j];
                hiddenDelta += hiddenOutputWeight[i][j]*delta[j];
            }
            inputHiddenWeight[i][oneHotInput] -= learnRate*hiddenDelta;
        }
    }
}
