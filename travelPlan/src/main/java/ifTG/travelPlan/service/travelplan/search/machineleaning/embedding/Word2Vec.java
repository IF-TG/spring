package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

import ifTG.travelPlan.service.travelplan.search.machineleaning.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.Backpropagation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class Word2Vec implements EmbeddingModel {
    private final Morpheme morpheme;
    private final Backpropagation backpropagation;

    @Override
    public WeightBuilder learningWeight(LearningBuilder builder) {
        validWeightBuilder(builder);
        for (int i = 0; i < builder.getEpoch(); i++) {
            learn(builder);
        }
        return builder.getWeightBuilder();
    }

    @Override
    public double[] forwardPassWithSoftmax(WeightBuilder weightBuilder, int oneHotInput) {
        return backpropagation.forwardPassWithSoftmaxForOneHotEncoding(
                weightBuilder.inputHiddenWeight,
                weightBuilder.hiddenOutputWeight,
                oneHotInput
        );
    }

    private void learn(LearningBuilder builder) {
        for (int i = 0; i< builder.getDocumentWordList().size(); i++){
            List<String> wordByDocument = builder.getDocumentWordList().get(i);
            initIdxAndBackpropagation(builder, wordByDocument);
        }
    }

    private void initIdxAndBackpropagation(LearningBuilder builder, List<String> wordByDocument) {
        for (int windowIdx = 0; windowIdx< wordByDocument.size(); windowIdx++){
            int startWindow = Math.max(0, windowIdx- builder.getWindow());
            int endWindow = Math.min(wordByDocument.size(), windowIdx+ builder.getWindow()+1);
            int oneHotInput = morpheme.getIdx(wordByDocument.get(windowIdx));
            for (int k = startWindow; k< endWindow; k++){
                if (k == windowIdx) continue;
                int oneHotOutput = morpheme.getIdx(wordByDocument.get(k));
                double[] result = forwardPassWithSoftmax(builder.getWeightBuilder(), oneHotInput);
                learnByBackpropagation(builder, oneHotInput, oneHotOutput, result);
            }
        }
    }

    private void learnByBackpropagation(LearningBuilder builder, int oneHotInput, int oneHotOutput, double[] result) {
        backpropagation.learnForOneHotEncoding(
                builder.getWeightBuilder().inputHiddenWeight,
                builder.getWeightBuilder().hiddenOutputWeight,
                builder.getLearnRate(),
                result,
                oneHotInput,
                oneHotOutput
        );
    }

    private void validWeightBuilder(LearningBuilder builder) {
        if (builder.getWeightBuilder()==null){
            initWeightBuilder(builder);
        }else{
            if (builder.getWeightBuilder().getHiddenOutputWeight()==null|| builder.getWeightBuilder().getInputHiddenWeight()==null){
                initWeightBuilder(builder);
            }
        }
    }

    private void initWeightBuilder(LearningBuilder builder) {
        WeightBuilder weightBuilder = initParameter(builder);
        builder.setWeightBuilder(weightBuilder);
    }
    private WeightBuilder initParameter(LearningBuilder builder) {
        double[][] inputHiddenWeight = initArrayToRandom(builder.getDimension(), morpheme.getWordIdxMap().size());
        double[][] hiddenOutputWeight = initArrayToRandom(builder.getDimension(), morpheme.getWordIdxMap().size());
        return WeightBuilder
                .builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight)
                .build();
    }


    private double[][] initArrayToRandom(int row, int col) {
        double[][] array = new double[row][col];
        Random random  = new Random();
        for (int i = 0; i<array.length; i++){
            for (int j = 0; j<array[i].length; j++){
                array[i][j] = -0.5 + random.nextDouble();
            }
        }
        return array;
    }

    @Override
    public void embedding(LearningBuilder builder) {
        
    }
}
