package ifTG.travelPlan.service.travelplan;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class Word2VecImpl implements Word2Vec{
    private final int DIMENSION = 100;
    private final double learnRate = 0.005;
    private double [][] inputHiddenWeight;
    private double [][] hiddenOutputWeight;
    private final Map<String, Integer> wordIdxMap = new HashMap<>();
    private final Morpheme morpheme;
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
        for (int i = 0; i<epoch; i++){
            double  e=1;
            learningWeight(nounList);
            if (i%10==0){
                double e2 = Arrays.stream(forwardPass(0)).sum();
                if (Math.abs(e2-e)<0.0001)break;
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

    @Override
    public double getScore(String s1, String s2){
        int oneHotInput = wordIdxMap.get(s1);
        int oneHotOutput = wordIdxMap.get(s2);
        return forwardPassWithSoftmax(oneHotInput)[oneHotOutput];
=======
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Word2VecImpl implements Word2Vec{
    private static Word2VecImpl word2Vec;
    private final double [][] inputHiddenWeight = new double[100][100];
    private final double [][] hiddenOutputWeight = new double[100][100];
    private final HashMap<String, Integer> wordIdxMap = new HashMap<>();

    public static Word2VecImpl getInstance(){
        if (word2Vec == null){
            word2Vec = new Word2VecImpl();
        }
        return word2Vec;
    }

    private Word2VecImpl(){

    }

    public double getScore(String s1, String s2){
        return 0;
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
    }
}
