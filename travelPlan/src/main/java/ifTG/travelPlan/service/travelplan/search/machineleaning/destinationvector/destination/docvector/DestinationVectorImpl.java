package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.docvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class DestinationVectorImpl implements DestinationVector{
    private final EmbeddingModel em;
    private double[][] destinationVector;
    private Map<Long, Integer> mapping;
    private final DestinationOverviewNounExtractor de;

    @Value("${nlp.doc2vec.learnRate}")
    private Double learnRate;

    @Value("${nlp.doc2vec.epoch}")
    private int epoch;

    @Value("${nlp.dimension}")
    private Integer dimension;

    @Value("${nlp.doc2vec.pv_dm.window}")
    private Integer window;

    private boolean isReady;

    public DestinationVectorImpl(@Qualifier("PvDMUsingDestinationOverviewVector") EmbeddingModel em, DestinationOverviewNounExtractor de) {
        this.em = em;
        this.de = de;
        this.isReady = false;
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
        isReady = true;
    }

    @Override
    public double[] getVectorByDestinationId(Long destinationId){
        if (!isReady)throw new RuntimeException("not ready destination vector");
        Integer mappingIdx = mapping.get(destinationId);
        if (mappingIdx==null){
            log.info("{} have no mappingIdx", destinationId);
            return null;
        }
        return destinationVector[mappingIdx];
    }

    @Override
    public double[] getVectorByIdx(Integer idx) {
        return destinationVector[idx];
    }

    @Override
    public Map<Long, Integer> getMapping(){
        return mapping;
    }

    private List<List<String>> initAndGetDocumentWordList() {
        Map<Long, List<String>> allNounMappingByDestination = de.findAllNounMappingByDestination();
        initArray(allNounMappingByDestination);
        return getDocumentWordList(allNounMappingByDestination);
    }

    private List<List<String>> getDocumentWordList(Map<Long, List<String>> allNounMappingByDestination) {
        List<List<String>> documentWordList = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(0);
        allNounMappingByDestination.keySet().forEach(destinationId->{
            mapping.put(destinationId, index.getAndIncrement());
            documentWordList.add(allNounMappingByDestination.get(destinationId));
        });
        return documentWordList;
    }

    private void initArray(Map<Long, List<String>> allNounMappingByDestination) {
        destinationVector = new double[allNounMappingByDestination.size()][dimension];
        mapping = new HashMap<>();
    }


}
