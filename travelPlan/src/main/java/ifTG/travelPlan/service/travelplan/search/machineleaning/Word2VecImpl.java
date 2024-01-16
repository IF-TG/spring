package ifTG.travelPlan.service.travelplan.search.machineleaning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/*@Service
@Slf4j
@Deprecated*/
/*
public class Word2VecImpl implements Word2Vec{
    private final int DIMENSION = 100;
    private final double learnRate = 0.005;
    private double [][] inputHiddenWeight;
    private double [][] hiddenOutputWeight;
    protected final Morpheme morpheme;
    private final int windowSize = 2;
    private final int epoch = 10;

    @Autowired
    public Word2VecImpl(Morpheme morpheme){
        this.morpheme = morpheme;
    }


    @Override
    public void initData(){
        List<String> nounList = morpheme.findAllNounByDestination();
        int count = 0;
        for (String s : nounList) {
            if (!wordIdxMap.containsKey(s)) {
                wordIdxMap.put(s, count);
                count++;
            }
        }
        initArray();
        double  e=1;
        for (int i = 0; i<epoch; i++){
            learningWeight(nounList);
            System.out.println("w1 > " + hiddenOutputWeight[0][0]);
            System.out.println("w2 > " + inputHiddenWeight[0][0]);
            System.out.println("epoch > " + i);
            if (i%10==0){
                double e2 = Arrays.stream(forwardPass(0)).sum();
                if (Math.abs(e2-e)<0.0001)break;
                e = e2;
            }
        }
    }

    private void learningWeight(List<String> nounList) {
        for (int i = 0; i< nounList.size()-windowSize; i++){
            int startWindow = Math.max(0, i-windowSize);
            int endWindow = Math.min(nounList.size(), i+windowSize+1);
            int windowIdx = switch (i) {
                case 0 -> 0;
                case 1 -> 1;
                default -> startWindow+windowSize;
            };
            int oneHotInput = wordIdxMap.get(nounList.get(windowIdx));
            for (int j = startWindow; j<endWindow; j++){
                if (j == windowIdx) continue;
                int oneHotOutput = wordIdxMap.get(nounList.get(j));
                double[] result = forwardPassWithSoftmax(oneHotInput);
                learn(result, oneHotInput, oneHotOutput);
            }

        }
    }

    private void learn(double[] result, int oneHotInput, int oneHotOutput) {
        double[] delta = new double[wordIdxMap.size()];
        for (int i =0; i<wordIdxMap.size(); i++){
            if (i == oneHotOutput){
                delta[i] = result[i]-1;
            }else{
                delta[i] = result[i];
            }
        }
        for (int i =0; i<DIMENSION; i++){
            double hiddenDelta = 0;
            for (int j =0; j<wordIdxMap.size(); j++){
                hiddenOutputWeight[i][j]-=learnRate*inputHiddenWeight[i][oneHotInput]*delta[j];
                hiddenDelta += hiddenOutputWeight[i][j]*delta[j];
            }
            inputHiddenWeight[i][oneHotInput] -= learnRate*hiddenDelta;
        }
    }

    private double[] forwardPass(int oneHotInput) {
        double[] result = new double[wordIdxMap.size()];
        for (int i =0; i<DIMENSION; i++){
            for (int j = 0; j<wordIdxMap.size(); j++){
                result[j] += inputHiddenWeight[i][oneHotInput]*hiddenOutputWeight[i][j];
            }
        }
        return result;
    }

    private void initArray() {
        inputHiddenWeight = new double[DIMENSION][wordIdxMap.size()];
        hiddenOutputWeight = new double[DIMENSION][wordIdxMap.size()];
        initArrayToRandom(inputHiddenWeight);
        initArrayToRandom(hiddenOutputWeight);
    }

    private void initArrayToRandom(double[][] weightArray) {
        Random random  = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < wordIdxMap.size(); j++) {
                weightArray[i][j] = -0.5 + random.nextDouble();
            }
        }
    }

    public double[] forwardPassWithSoftmax(int oneHotInput){
        double[] result = forwardPass(oneHotInput);
        double[] softmax = new double[result.length];
        double tmp = 0;
        double max = 0;
        for (double v : result){
            if (max<v){
                max = v;
            }
        }
        for (double v : result) {
            tmp += Math.exp(v-max);
        }
        for (int i =0; i<result.length; i++){
            softmax[i] = Math.exp(result[i]-max)/tmp;
        }
        return softmax;
    }
}
*/
