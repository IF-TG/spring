package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.WordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.filereader.WordVectorFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class DestinationWordVectorV2 implements DestinationWordVector {
    private final EmbeddingModel em;
    protected final DestinationOverviewNounExtractor de;
    protected double [][] inputHiddenWeight;
    private double [][] hiddenOutputWeight;
    private boolean isReady;
    private final WordVectorFileReader wordVectorFileReader;

    @Value("${nlp.dimension}")
    protected Integer dimension;
    @Value("${nlp.word2vec.learnRate}")
    private Double learnRate;
    @Value("${nlp.word2vec.window}")
    private Integer windowSize;
    @Value("${nlp.word2vec.epoch}")
    private Integer epoch;
    private Integer idx;


    @Autowired
    public DestinationWordVectorV2(DestinationOverviewNounExtractor de, @Qualifier("skipGram") EmbeddingModel em, WordVectorFileReader wordVectorFileReader){
        this.de = de;
        this.em = em;
        this.wordVectorFileReader = wordVectorFileReader;
    }


    @Override
    public void initData(){
        
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
        isReady = true;
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
        Integer idx = de.getIdx(s);
        return inputHiddenWeight[idx];
    }

    @Override
    public double[] getVectorByMorphemeIdx(int morphemeIdx) {
        if (!isReady)throw new RuntimeException("word2vec is not ready");
        if (inputHiddenWeight.length<idx)throw new RuntimeException("morphemeIdx out of bounds");
        return inputHiddenWeight[morphemeIdx];
    }

    @Override
    public double[][] getInputHiddenWeight(){
        return inputHiddenWeight;
    }

    @Override
    public double[][] getHiddenOutputWeight(){
        return hiddenOutputWeight;
    }


    protected double[] forwardPassWithSoftmax(int idx){
        return em.forwardPassWithSoftmax(
                WeightBuilder.builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight).build()
                , idx);
    }
}
