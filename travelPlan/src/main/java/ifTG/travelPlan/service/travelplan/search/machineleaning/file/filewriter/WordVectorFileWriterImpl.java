package ifTG.travelPlan.service.travelplan.search.machineleaning.file.filewriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifTG.travelPlan.aop.Time;
import ifTG.travelPlan.service.filestore.FileStore;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.DestinationWordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.WordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.vector.VectorCalculating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WordVectorFileWriterImpl implements WordVectorFileWriter {
    private final FileStore fileStore;
    private final Morpheme morpheme;
    private final DestinationWordVector destinationWordVector;

    @Value("${nlp.word2vec.file.similarity.write.path}")
    private String wordSimilarityPath;

    @Value("${nlp.word2vec.file.weight.write.path}")
    private String wordWeightPath;

    @Override
    @Time
    public void saveFile(){
        String createAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh.mm.ss"));
        saveWordSimilarityFile(createAt);
        saveWordWeightAndMapping(createAt);
    }

    private void saveWordSimilarityFile(String filename) {
        StringBuilder text = new StringBuilder();
        Map<Integer, String> wordMap = morpheme.getWordMap();
        int nounNum = wordMap.size();
        for (int i =0; i<nounNum; i++){
            String wordA = wordMap.get(i);
            double[] vectorA = destinationWordVector.getVectorByMorphemeIdx(i);
            for (int j = i+1; j<nounNum; j++){
                String wordB = wordMap.get(j);
                double[] vectorB = destinationWordVector.getVectorByMorphemeIdx(j);
                String cosineSimilarity = String.format("%.1f", (VectorCalculating.cosineSimilarity(vectorA, vectorB)+1)/2*100);
                text.append(wordA).append("\t").append(wordB).append("\t").append(cosineSimilarity).append("%").append("\n");
            }
        }
        fileStore.saveTextFile(text.toString(), wordSimilarityPath+filename);
    }

    public void saveWordWeightAndMapping(String fileName){
        WordVector wordVector = new WordVector(
                destinationWordVector.getInputHiddenWeight(),
                destinationWordVector.getHiddenOutputWeight(),
                destinationWordVector.getHiddenOutputWeight().length,
                morpheme.getWordIdxMap()
        );
        try{
            fileStore.saveTextFile(new ObjectMapper().writeValueAsString(wordVector), wordWeightPath+fileName);
        } catch (JsonProcessingException e) {
            log.debug("잘못된 형식의 json", e);
        }
    }
}
