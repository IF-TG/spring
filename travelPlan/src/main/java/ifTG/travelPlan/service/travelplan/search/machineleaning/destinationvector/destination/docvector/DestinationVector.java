package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import java.util.Map;

public interface DestinationVector {
    void init();

    double[] getVectorByDestinationId(Long destinationId);

    Map<Long, Integer> getMapping();
}
