package ifTG.travelPlan.service.travelplan.search.machineleaning.vector;

import java.util.List;
import java.util.Map;

public interface VectorAverage {
    double[] getVectorAverage(List<Map<Integer, Double>> vecList);
}
