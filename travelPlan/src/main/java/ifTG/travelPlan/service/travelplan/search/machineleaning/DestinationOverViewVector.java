package ifTG.travelPlan.service.travelplan.search.machineleaning;

import java.util.Map;

public interface DestinationOverViewVector {
    void initData();

    Map<Integer, Double> getVectorByString(String s);
}