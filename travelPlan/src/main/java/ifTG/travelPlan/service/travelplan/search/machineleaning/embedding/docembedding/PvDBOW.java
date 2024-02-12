package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.docembedding;

import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.Backpropagation;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.wordembedding.Word2Vec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component("pvDBOW")
@RequiredArgsConstructor
@Slf4j
public class PvDBOW implements Doc2Vec {
    private final Backpropagation bp;
    private final Word2Vec word2Vec;
    private final Morpheme morpheme;

    @Override
    public WeightBuilder learningWeight(LearningBuilder builder) {
        validWeightBuilder(builder);
        for (int i = 0; i<builder.getEpoch(); i++){
            learn(builder);
        }
        return builder.getWeightBuilder();
    }

    private void learn(LearningBuilder builder) {
        for (int oneHotInput = 0; oneHotInput<builder.getDocumentWordList().size(); oneHotInput++){
            List<String> wordListByDocument = builder.getDocumentWordList().get(oneHotInput);
            for (int oneHotOutput = 0; oneHotOutput<wordListByDocument.size(); oneHotOutput++){
                double[] result = forwardPassWithSoftmax(builder.getWeightBuilder() ,oneHotInput);
                bp.learnForOneHotEncoding(
                        builder.getWeightBuilder().getInputHiddenWeight(),
                        builder.getWeightBuilder().getHiddenOutputWeight(),
                        builder.getLearnRate(),
                        result,
                        oneHotInput,
                        oneHotOutput
                );
            }
        }
    }

    @Override
    public double[] forwardPassWithSoftmax(WeightBuilder weightBuilder, int oneHotInput) {
        return word2Vec.forwardPassWithSoftmax(weightBuilder, oneHotInput);
    }



    public void validWeightBuilder(LearningBuilder builder) {
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
        double[][] inputHiddenWeight = initArrayToRandom(builder.getDimension(), builder.getDocumentWordList().size());
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
}
