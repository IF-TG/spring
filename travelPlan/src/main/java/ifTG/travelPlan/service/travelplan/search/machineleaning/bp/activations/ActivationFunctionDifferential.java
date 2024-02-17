package ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations;

import java.util.function.Function;

public final class ActivationFunctionDifferential {
    public static Function<Double, Double> linear(){
        return input -> 1.0;
    }
}
