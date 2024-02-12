package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.docembedding.doc2vec;

import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.DestinationOverViewVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.wordembedding.word2vec.Word2Vec;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.InitArray;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("PvDMUsingDestinationOverviewVector")
@RequiredArgsConstructor
public class PvDMUsingDestinationOverviewVector implements Doc2Vec{
    private final Morpheme morpheme;
    private final Word2Vec word2Vec;
    private final DestinationOverViewVector dv;

    @Override
    public WeightBuilder learningWeight(LearningBuilder builder) {
        validWeightBuilder(builder);

        for (int i = 0; i<builder.getEpoch(); i++){
            for (int j = 0; j< builder.getDocumentWordList().size(); j++){
                List<String> wordList = builder.getDocumentWordList().get(j);
                for (int k = 0; k< builder.getWindow(); k++){

                }
            }
        }

        return ;
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
        double[][] inputHiddenWeight = InitArray.initArrayToRandom(builder.getDocumentWordList().size(), builder.getDimension());
        double[][] hiddenOutputWeight = InitArray.initArrayToRandom(builder.getDimension(), morpheme.getWordIdxMap().size());
        return WeightBuilder
                .builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight)
                .build();
    }
}
