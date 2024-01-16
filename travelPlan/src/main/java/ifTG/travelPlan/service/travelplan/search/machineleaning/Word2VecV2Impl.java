package ifTG.travelPlan.service.travelplan.search;

import ifTG.travelPlan.exception.CustomErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public abstract class Word2VecV2Impl implements Word2Vec{

    @Value("${nlp.dimension}")
    protected Integer DIMENSION;
    @Value("${nlp.word2vec.learnRate}")
    private Double learnRate;
    protected double [][] inputHiddenWeight;
    private double [][] hiddenOutputWeight;
    protected final Morpheme morpheme;
    @Value("${nlp.window}")
    private Integer windowSize;
    @Value("${nlp.word2vec.epoch}")
    private Integer epoch;
    private Integer size;
    private boolean isReady;

    @Autowired
    public Word2VecV2Impl(Morpheme morpheme){
        this.morpheme = morpheme;
    }


    @Override
    public void initData(){
        isReady = true;
        size = morpheme.getWordIdxMap().size();
        List<String> nounList = morpheme.findAllNounByDestination();
        initArray();
        for (int i = 0; i<epoch; i++){
            learningWeight(nounList);
            System.out.println("w1 > " + hiddenOutputWeight[0][0]);
            System.out.println("w2 > " + inputHiddenWeight[0][0]);
            System.out.println("epoch > " + i);
        }
    }

    @Override
    public Map<Integer, Double> getVectorByString(String s){
        if (!isReady)throw new RuntimeException("not ready");
        Map<Integer, Double> resultMap = new HashMap<>();
        Integer idx = morpheme.getIdx(s);
        for (int i = 0; i<DIMENSION; i++){
            resultMap.put(i,inputHiddenWeight[i][idx]);
        }
        return resultMap;
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
            int oneHotInput = morpheme.getIdx(nounList.get(windowIdx));
            for (int j = startWindow; j<endWindow; j++){
                if (j == windowIdx) continue;
                int oneHotOutput = morpheme.getIdx(nounList.get(j));
                double[] result = forwardPassWithSoftmax(oneHotInput);
                learn(result, oneHotInput, oneHotOutput);
            }
        }
    }

    private double[] forwardPass(int oneHotInput) {
        double[] result = new double[size];
        for (int i =0; i<DIMENSION; i++){
            for (int j = 0; j<size; j++){
                result[j] += inputHiddenWeight[i][oneHotInput]*hiddenOutputWeight[i][j];
            }
        }
        return result;
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

    private void learn(double[] result, int oneHotInput, int oneHotOutput) {
        double[] delta = new double[size];
        for (int i =0; i<size; i++){
            if (i == oneHotOutput){
                delta[i] = result[i]-1;
            }else{
                delta[i] = result[i];
            }
        }
        for (int i =0; i<DIMENSION; i++){
            double hiddenDelta = 0;
            for (int j =0; j<size; j++){
                hiddenOutputWeight[i][j]-=learnRate*inputHiddenWeight[i][oneHotInput]*delta[j];
                hiddenDelta += hiddenOutputWeight[i][j]*delta[j];
            }
            inputHiddenWeight[i][oneHotInput] -= learnRate*hiddenDelta;
        }
    }



    private void initArray() {
        inputHiddenWeight = new double[DIMENSION][morpheme.getWordIdxMap().size()];
        hiddenOutputWeight = new double[DIMENSION][morpheme.getWordIdxMap().size()];
        initArrayToRandom(inputHiddenWeight);
        initArrayToRandom(hiddenOutputWeight);
    }

    private void initArrayToRandom(double[][] weightArray) {
        Random random  = new Random();
        int size = morpheme.getWordIdxMap().size();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < size; j++) {
                weightArray[i][j] = -0.5 + random.nextDouble();
            }
        }
    }


    public abstract double getScore(double[] s1, double[] s2);
}
