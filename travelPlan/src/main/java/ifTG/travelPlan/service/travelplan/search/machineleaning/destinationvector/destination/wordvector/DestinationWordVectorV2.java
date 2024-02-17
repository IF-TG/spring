package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector;

import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class DestinationWordVectorV2 implements DestinationWordVector {

    protected double [][] inputHiddenWeight;
    protected double [][] hiddenOutputWeight;
    private boolean isReady;
    protected final DestinationOverviewNounExtractor de;

    @Autowired
    public DestinationWordVectorV2(DestinationOverviewNounExtractor de) {
        this.de = de;
    }

    @Override
    public void initData(
            double[][] inputHiddenWeight,
            double[][] hiddenOutputWeight
    ){
        this.inputHiddenWeight = inputHiddenWeight;
        this.hiddenOutputWeight = hiddenOutputWeight;
        isReady = true;
    }

    @Override
    public Map<Integer, Double> getVectorMapByString(String s){
        if (!isReady)throw new RuntimeException("word2vec is not ready");
        Map<Integer, Double> resultMap = new HashMap<>();
        Integer idx = de.getIdx(s);
        int dimension = hiddenOutputWeight.length;
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
        if (inputHiddenWeight.length<morphemeIdx)throw new RuntimeException("morphemeIdx out of bounds");
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

}
