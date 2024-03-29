package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.wordembedding;

import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.Backpropagation;
import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations.ActivationFunction;
import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations.ActivationFunctionDifferential;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.NormalizedVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.InitArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("skipGram")
@Slf4j
@RequiredArgsConstructor
public class SkipGram implements Word2Vec {
    private final Morpheme morpheme;
    private final Backpropagation backpropagation;

    @Override
    public WeightBuilder learningWeight(LearningBuilder builder) {
        validWeightBuilder(builder);
        for (int i = 0; i < builder.getEpoch(); i++) {
            learn(builder);
        }
        normalized(builder);
        return builder.getWeightBuilder();
    }

    private void normalized(LearningBuilder builder) {
        double[][] vectors = builder.getWeightBuilder().getInputHiddenWeight();
        for (double[] vec : vectors){
            NormalizedVector.normalizedVector(vec);
        }
    }

    @Override
    public double[] forwardPassWithSoftmax(WeightBuilder weightBuilder, int oneHotInput) {
        return backpropagation.forwardPassWithSoftmaxForOneHotEncoding(
                weightBuilder.getInputHiddenWeight(),
                weightBuilder.getHiddenOutputWeight(),
                oneHotInput,
                ActivationFunction.linear()
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
            int oneHotInput = getOneHotIdx(wordByDocument.get(windowIdx));
            int startWindow = Math.max(0, windowIdx- builder.getWindow());
            int endWindow = Math.min(wordByDocument.size(), windowIdx+ builder.getWindow()+1);
            for (int k = startWindow; k< endWindow; k++){
                if (k == windowIdx) continue;
                int oneHotOutput = getOneHotIdx(wordByDocument.get(k));
                double[] result = forwardPassWithSoftmax(builder.getWeightBuilder(), oneHotInput);
                learnByBackpropagation(builder, oneHotInput, oneHotOutput, result);
            }
        }
    }

    private int getOneHotIdx(String s) {
        return morpheme.getIdx(s);
    }

    private void learnByBackpropagation(LearningBuilder builder, int oneHotInput, int oneHotOutput, double[] result) {

        backpropagation.learnForOneHotEncodingWithSoftmax(
                builder.getWeightBuilder().getInputHiddenWeight(),
                builder.getWeightBuilder().getHiddenOutputWeight(),
                builder.getLearnRate(),
                result,
                oneHotInput,
                oneHotOutput,
                ActivationFunctionDifferential.linear()
        );

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
        double[][] inputHiddenWeight = InitArray.initArrayToRandom(morpheme.getWordIdxMap().size(), builder.getDimension());
        double[][] hiddenOutputWeight = InitArray.initArrayToRandom(builder.getDimension(), morpheme.getWordIdxMap().size());
        return WeightBuilder
                .builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight)
                .build();
    }


}
