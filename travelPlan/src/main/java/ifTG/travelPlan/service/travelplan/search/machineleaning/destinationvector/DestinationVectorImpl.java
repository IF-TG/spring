package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DestinationVectorImpl implements DestinationVector{
    private final EmbeddingModel em;
    private double[][] destinationVector;
<<<<<<< HEAD
    private Map<Integer, Long> mapping;
    private final DestinationRepository destinationRepository;
=======
    private Map<Long, Long> mapping;
    private final DestinationOverviewNounExtractor de;
>>>>>>> 8bcb5df2ff3540b25939591164167be063070098

    @Value("${nlp.doc2vec.learnRate}")
    private Double learnRate;

    @Value("${nlp.doc2vec.epoch}")
    private int epoch;

    @Value("${nlp.dimension}")
    private Integer dimension;

    @Value("${nlp.doc2vec.pv_dm.window}")
    private Integer window;

    public DestinationVectorImpl(@Qualifier("PvDMUsingDestinationOverviewVector") EmbeddingModel em, DestinationOverviewNounExtractor de) {
        this.em = em;
        this.de = de;
    }

    @Override
    public void init(){
        destinationVector = em.learningWeight(
                LearningBuilder.builder()
                        .epoch(epoch)
                        .learnRate(learnRate)
                        .dimension(dimension)
                        .documentWordList(initAndGetDocumentWordList())
                        .window(window)
                        .build()
        ).getInputHiddenWeight();
    }

<<<<<<< HEAD
        
=======
    private List<List<String>> initAndGetDocumentWordList() {
        Map<Long, List<String>> allNounMappingByDestination = de.findAllNounMappingByDestination();
        initArray(allNounMappingByDestination);
        return getDocumentWordList(allNounMappingByDestination);
    }

    private List<List<String>> getDocumentWordList(Map<Long, List<String>> allNounMappingByDestination) {
        List<List<String>> documentWordList = new ArrayList<>();
        AtomicLong index = new AtomicLong(0);
        allNounMappingByDestination.keySet().forEach(destinationId->{
            mapping.put(destinationId, index.getAndIncrement());
            documentWordList.add(allNounMappingByDestination.get(destinationId));
        });
        return documentWordList;
    }
>>>>>>> 8bcb5df2ff3540b25939591164167be063070098

    private void initArray(Map<Long, List<String>> allNounMappingByDestination) {
        destinationVector = new double[allNounMappingByDestination.size()][dimension];
        mapping = new HashMap<>();
    }


}
