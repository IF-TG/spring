package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Slf4j

public abstract class DestinationOverViewVectorV2 implements DestinationOverViewVector {
    private final EmbeddingModel em;
    protected final DestinationOverviewNounExtractor de;
    protected double [][] inputHiddenWeight;
    private double [][] hiddenOutputWeight;
    private boolean isReady;

    @Value("${nlp.dimension}")
    protected Integer dimension;
    @Value("${nlp.word2vec.learnRate}")
    private Double learnRate;
    @Value("${nlp.window}")
    private Integer windowSize;
    @Value("${nlp.word2vec.epoch}")
    private Integer epoch;


    @Autowired
    public DestinationOverViewVectorV2(DestinationOverviewNounExtractor de, @Qualifier("skipGram") EmbeddingModel em){
        this.de = de;
        this.em = em;
    }


    @Override
    public void initData(){
        isReady = true;
        List<List<String>> nounListGroupByDestination = de.findAllNounGroupByDestination();
        WeightBuilder weightBuilder = em.learningWeight(
                LearningBuilder.builder()
                        .learnRate(learnRate)
                        .epoch(epoch)
                        .dimension(dimension)
                        .window(windowSize)
                        .documentWordList(nounListGroupByDestination)
                        .build()
        );
        inputHiddenWeight = weightBuilder.getInputHiddenWeight();
        hiddenOutputWeight = weightBuilder.getHiddenOutputWeight();
    }

    @Override
    public Map<Integer, Double> getVectorMapByString(String s){
        if (!isReady)throw new RuntimeException("word2vec is not ready");
        Map<Integer, Double> resultMap = new HashMap<>();
        Integer idx = de.getIdx(s);
        for (int i = 0; i< dimension; i++){
            resultMap.put(i,inputHiddenWeight[idx][i]);
        }
        return resultMap;
    }

    @Override
    public double[] getVectorByString(String s){
        if (!isReady)throw new RuntimeException("word2vec is not ready");
        de.getIdx(s);
        return null;
    }

    protected double[] forwardPassWithSoftmax(int idx){
        return em.forwardPassWithSoftmax(
                WeightBuilder.builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight).build()
                , idx);
    }

    public abstract double getScore(double[] s1, double[] s2);
}
