package ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActivationFunction {
    public static Function<double[], double[]> softmaxArray(){
        return input->{
            double[] softmax = new double[input.length];
            double max = getMax(input);
            double exp = getExp(input, max);
            for (int i = 0; i< input.length; i++){
                softmax[i] = Math.exp(input[i]-max)/exp;
            }
            return softmax;
        };
    }

    public static Function<Double, Double> linear(){
        return input->input;
    }

    private static double getExp(double[] result, double max) {
        double tmp = 0;
        for (double v : result) {
            tmp += Math.exp(v- max);
        }
        return tmp;
    }

    private static double getMax(double[] result) {
        return Arrays.stream(result).max().orElse(Double.NEGATIVE_INFINITY);
    }
}
