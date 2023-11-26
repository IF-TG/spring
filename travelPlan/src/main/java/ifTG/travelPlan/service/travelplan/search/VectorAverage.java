package ifTG.travelPlan.service.travelplan.search;

import java.util.List;
import java.util.Map;

public interface VectorAverage {
    double[] getVectorAverage(List<Map<Integer, Double>> vecList);
}
