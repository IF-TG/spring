package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DestinationVectorImpl implements DestinationVector{
    private final EmbeddingModel em;
    private double[][] destinationVector;
    private final Map<Integer, Integer> mapping;
    private final DestinationRepository destinationRepository;

    public DestinationVectorImpl(@Qualifier("pvDBOW") EmbeddingModel em, DestinationRepository destinationRepository) {
        this.em = em;
        this.destinationRepository = destinationRepository;
        mapping = new HashMap<>();
    }

    public init


}
