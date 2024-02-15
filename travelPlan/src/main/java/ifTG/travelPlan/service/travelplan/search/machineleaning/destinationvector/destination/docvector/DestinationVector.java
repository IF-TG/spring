package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.docvector;

import java.util.Map;

public interface DestinationVector {
    void init();

    double[] getVectorByDestinationId(Long destinationId);

    double[] getVectorByIdx(Integer idx);

    Map<Long, Integer> getMapping();
}
