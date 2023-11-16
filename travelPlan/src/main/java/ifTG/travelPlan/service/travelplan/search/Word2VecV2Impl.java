package ifTG.travelPlan.service.travelplan.search;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Word2Vec2VImpl implements Word2Vec{
    protected final int DIMENSION = 100;
    private final double learnRate = 0.005;
    protected double [][] inputHiddenWeight;
    private double [][] hiddenOutputWeight;
    protected final Morpheme morpheme;
    private final int windowSize = 2;
    private final int epoch = 20;
    private Integer size;

    @Autowired
    public Word2Vec2VImpl(Morpheme morpheme){
        this.morpheme = morpheme;
    }


    @Override
    public void initData(){
        size = morpheme.getWordIdxMap().size();
        List<String> nounList = morpheme.findAllNounByDestination();
        initArray();
        double  e=1;
        for (int i = 0; i<epoch; i++){
            learningWeight(nounList);
            System.out.println("test  = " + forwardPassWithSoftmax(0)[1]);
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
            int oneHotInput = morpheme.getIdx(nounList.get(windowIdx));
            for (int j = startWindow; j<endWindow; j++){
                if (j == windowIdx) continue;
                int oneHotOutput = morpheme.getIdx(nounList.get(j));
                double[] result = forwardPassWithSoftmax(oneHotInput);
                learn(result, oneHotInput, oneHotOutput);
            }
        }
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

    private double[] forwardPass(int oneHotInput) {
        double[] result = new double[size];
        for (int i =0; i<DIMENSION; i++){
            for (int j = 0; j<size; j++){
                result[j] += inputHiddenWeight[i][oneHotInput]*hiddenOutputWeight[i][j];
            }
        }
        return result;
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
