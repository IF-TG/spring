package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.docembedding.doc2vec;

import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.Backpropagation;
import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations.ActivationFunction;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.wordembedding.word2vec.Word2Vec;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.InitArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
                bp.learnForOneHotEncodingWithSoftmax(
                        builder.getWeightBuilder().getInputHiddenWeight(),
                        builder.getWeightBuilder().getHiddenOutputWeight(),
                        builder.getLearnRate(),
                        result,
                        oneHotInput,
                        oneHotOutput,
                        ActivationFunction.linear()
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
        double[][] inputHiddenWeight = InitArray.initArrayToRandom(builder.getDimension(), builder.getDocumentWordList().size());
        double[][] hiddenOutputWeight = InitArray.initArrayToRandom(builder.getDimension(), morpheme.getWordIdxMap().size());
        return WeightBuilder
                .builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight)
                .build();
    }



}
