package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import java.util.Map;

public interface DestinationWordVector {
    void initData();
    Map<Integer, Double> getVectorMapByString(String s);
    double[] getVectorByString(String s);
    double[] getVectorByMorphemeIdx(int idx);

    double[][] getInputHiddenWeight();

    double[][] getHiddenOutputWeight();
}
