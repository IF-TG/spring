package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeightBuilder {
    double[][] inputHiddenWeight;
    double[][] hiddenOutputWeight;
    double[][][] hiddenWeight;
}
