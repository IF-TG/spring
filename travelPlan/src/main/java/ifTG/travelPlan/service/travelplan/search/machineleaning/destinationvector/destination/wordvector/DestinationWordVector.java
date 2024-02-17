package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector;

import java.util.Map;

public interface DestinationWordVector {

    void initData(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight
    );

    Map<Integer, Double> getVectorMapByString(String s);
    double[] getVectorByString(String s);
    double[] getVectorByMorphemeIdx(int idx);

    double[][] getInputHiddenWeight();

    double[][] getHiddenOutputWeight();
}
