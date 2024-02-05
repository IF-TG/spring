package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DestinationVectorImpl implements DestinationVector{
    private final EmbeddingModel em;
    private double[][] destinationVector;
    private Map<Long, Long> mapping;
    private final DestinationRepository destinationRepository;

    public DestinationVectorImpl(@Qualifier("pvDBOW") EmbeddingModel em, DestinationRepository destinationRepository) {
        this.em = em;
        this.destinationRepository = destinationRepository;
    }

    public void init(){
        List<Destination> allDestinationList = destinationRepository.findAll();
        mapping = IntStream.range(0, allDestinationList.size()).boxed()
                .collect(Collectors.toMap(i->i, i->allDestinationList.get(i).getId()));



    }


}
