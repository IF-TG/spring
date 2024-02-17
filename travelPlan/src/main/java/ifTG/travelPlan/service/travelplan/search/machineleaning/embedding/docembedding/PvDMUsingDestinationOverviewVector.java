package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.docembedding;

import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.Backpropagation;
import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations.ActivationFunction;
import ifTG.travelPlan.service.travelplan.search.machineleaning.bp.activations.ActivationFunctionDifferential;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.DestinationWordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.wordembedding.Word2Vec;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.InitArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("PvDMUsingDestinationOverviewVector")
@RequiredArgsConstructor
@Slf4j
public class PvDMUsingDestinationOverviewVector implements Doc2Vec {
    private final Morpheme morpheme;
    private final Word2Vec word2Vec;
    private final DestinationWordVector dv;
    private final Backpropagation bp;

    @Override
    public WeightBuilder learningWeight(LearningBuilder builder) {
        validWeightBuilder(builder);

        for (int i = 0; i<builder.getEpoch(); i++){
            learn(builder);
        }

        return builder.getWeightBuilder();
    }

    private void learn(LearningBuilder builder) {
        for (int documentIdx = 0; documentIdx< builder.getDocumentWordList().size(); documentIdx++){
            validWordListSize(builder, documentIdx);
            int wordListSize = builder.getDocumentWordList().get(documentIdx).size();
            for (int targetIdx = builder.getWindow(); targetIdx<wordListSize; targetIdx++){
                double[] inputVector = getInputVector(builder, documentIdx, targetIdx);
                double[] result = forwardPass(builder, inputVector);
                learning(builder, documentIdx, targetIdx, inputVector, result);
            }
        }
    }

    private static void validWordListSize(LearningBuilder builder, int documentIdx) {
        if( builder.getDocumentWordList().get(documentIdx).size()<= builder.getWindow())
            throw new RuntimeException("The length of the document " + documentIdx + " is shorter than the window size, so it will not be trained.");
    }

    private double[] getInputVector(LearningBuilder builder, int documentIdx, int targetIdx) {
        double[] inputVector = new double[builder.getDimension()];
        arraySum(inputVector, builder.getWeightBuilder().getInputHiddenWeight()[documentIdx]);
        for (int inputVectorIdx = targetIdx - builder.getWindow(); inputVectorIdx< targetIdx; inputVectorIdx++){
            double[] inputWordVector = dv.getVectorByString( builder.getDocumentWordList().get(documentIdx).get(inputVectorIdx));
            arraySum(inputVector, inputWordVector);
        }
        divideVector(inputVector, builder.getWindow()+1);
        return inputVector;
    }

    private double[] forwardPass(LearningBuilder builder, double[] inputVector) {
        return bp.forwardPassVectorWithSoftmax(
                inputVector,
                builder.getWeightBuilder().getHiddenOutputWeight(),
                ActivationFunction.linear()
        );
    }

    private void learning(LearningBuilder builder, int documentIdx, int targetIdx, double[] inputVector, double[] result) {
        bp.learnVectorForOneHotOutputWithSoftmax(
                inputVector,
                builder.getWeightBuilder().getInputHiddenWeight()[documentIdx],
                builder.getWindow()+1.0,
                builder.getWeightBuilder().getHiddenOutputWeight(),
                builder.getLearnRate(),
                result,
                targetIdx,
                ActivationFunctionDifferential.linear()
        );
    }

    private static void divideVector(double[] inputVector, double divisor) {
        for (int dimensionIdx = 0; dimensionIdx < inputVector.length; dimensionIdx++){
            inputVector[dimensionIdx] /= divisor;
        }
    }

    private static void arraySum(double[] baseArray, double[] array){
        for (int i = 0; i<baseArray.length; i++){
            baseArray[i] += array[i];
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
        double[][] inputHiddenWeight = InitArray.initArrayToRandom(builder.getDocumentWordList().size(), builder.getDimension());
        double[][] hiddenOutputWeight = InitArray.initArrayToRandom(builder.getDimension(), morpheme.getWordIdxMap().size());
        return WeightBuilder
                .builder()
                .inputHiddenWeight(inputHiddenWeight)
                .hiddenOutputWeight(hiddenOutputWeight)
                .build();
    }
}
