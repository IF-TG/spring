package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import java.util.Map;

public interface DestinationOverViewVector {
    void initData();
    Map<Integer, Double> getVectorMapByString(String s);

    double[] getVectorByString(String s);
}
