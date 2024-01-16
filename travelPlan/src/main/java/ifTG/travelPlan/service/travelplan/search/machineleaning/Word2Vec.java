package ifTG.travelPlan.service.travelplan.search.machineleaning;

import java.util.Map;

public interface Word2Vec {
    void initData();

    Map<Integer, Double> getVectorByString(String s);
}
