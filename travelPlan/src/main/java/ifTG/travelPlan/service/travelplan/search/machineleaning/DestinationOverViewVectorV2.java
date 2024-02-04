package ifTG.travelPlan.service.travelplan.search.machineleaning;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j

public abstract class DestinationOverViewVectorV2 implements DestinationOverViewVector {
    private final EmbeddingModel em;
    protected final Morpheme morpheme;
    private final DestinationOverviewNounExtractor de;
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
    public DestinationOverViewVectorV2(DestinationOverviewNounExtractor de, EmbeddingModel em, Morpheme morpheme){
        this.de = de;
        this.em = em;
        this.morpheme = morpheme;
    }


    @Override
    public void initData(){
        isReady = true;
        Integer size = morpheme.getWordIdxMap().size();
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
    public Map<Integer, Double> getVectorByString(String s){
        if (!isReady)throw new RuntimeException("word2vec is not ready");
        Map<Integer, Double> resultMap = new HashMap<>();
        Integer idx = morpheme.getIdx(s);
        for (int i = 0; i< dimension; i++){
            resultMap.put(i,inputHiddenWeight[i][idx]);
        }
        return resultMap;
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
