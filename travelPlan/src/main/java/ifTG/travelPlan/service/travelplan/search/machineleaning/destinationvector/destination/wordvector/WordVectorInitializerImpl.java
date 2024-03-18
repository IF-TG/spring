package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector;

import ifTG.travelPlan.aop.Time;
import ifTG.travelPlan.service.destination.morpheme.DestinationOverviewNounExtractor;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.EmbeddingModel;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.LearningBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.embedding.WeightBuilder;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.WordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.filereader.WordVectorFileReader;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.filewriter.WordVectorFileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class WordVectorInitializerImpl implements WordVectorInitializer {

    @Value("${nlp.dimension}")
    protected Integer dimension;
    @Value("${nlp.word2vec.learnRate}")
    private Double learnRate;
    @Value("${nlp.word2vec.window}")
    private Integer windowSize;
    @Value("${nlp.word2vec.epoch}")
    private Integer epoch;

    private final Morpheme morpheme;
    private final DestinationOverviewNounExtractor de;
    private final EmbeddingModel em;
    private final DestinationWordVector destinationWordVector;
    private final WordVectorFileReader wordVectorFileReader;
    private final WordVectorFileWriter wordVectorFileWriter;

    public WordVectorInitializerImpl(Morpheme morpheme, DestinationOverviewNounExtractor de, @Qualifier("skipGram") EmbeddingModel em, DestinationWordVector destinationWordVector, WordVectorFileReader wordVectorFileReader, WordVectorFileWriter wordVectorFileWriter) {
        this.morpheme = morpheme;
        this.de = de;
        this.em = em;
        this.destinationWordVector = destinationWordVector;
        this.wordVectorFileReader = wordVectorFileReader;
        this.wordVectorFileWriter = wordVectorFileWriter;
    }

    @Override
    @Time
    public void initDestinationWordVector(){
        WordVector wordVector = wordVectorFileReader.readWordWeight();
        if (valid(wordVector)){
            log.info("파일로부터 연결강도 설정");
            morpheme.init(wordVector.getMapping());
            destinationWordVector.initData(
                    wordVector.getInputHiddenWeight(),
                    wordVector.getHiddenOutputWeight()
            );
        }else{
            morpheme.init();
            createWordWeight();
            wordVectorFileWriter.saveFile();
        }
    }

    private boolean valid(WordVector wordVector) {
        return wordVector != null && morpheme.isValid(wordVector.getMapping()) && wordVector.getDimension() == dimension;
    }

    private void createWordWeight() {
        WeightBuilder weightBuilder = learningWeightByEmbeddingModel();
        destinationWordVector.initData(
                weightBuilder.getInputHiddenWeight(),
                weightBuilder.getHiddenOutputWeight()
        );
    }

    private WeightBuilder learningWeightByEmbeddingModel() {
        List<List<String>> nounListGroupByDestination = de.findAllNounGroupByDestination();
        return em.learningWeight(
                LearningBuilder.builder()
                        .learnRate(learnRate)
                        .epoch(epoch)
                        .dimension(dimension)
                        .window(windowSize)
                        .documentWordList(nounListGroupByDestination)
                        .build()
        );
    }
}
