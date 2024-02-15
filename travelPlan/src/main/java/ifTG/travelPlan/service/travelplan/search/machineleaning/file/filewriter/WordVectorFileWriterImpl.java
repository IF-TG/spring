package ifTG.travelPlan.service.travelplan.search.machineleaning.fileWriter;

import ifTG.travelPlan.service.filestore.FileStore;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.DestinationWordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.vector.VectorCalculating;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WordVectorFileWriterImpl implements WordVectorFileWriter {
    private final FileStore fileStore;
    private final Morpheme morpheme;
    private final DestinationWordVector destinationWordVector;

    @Value("${nlp.word2vec.similarity.write.path}")
    private String wordSimilarityPath;

    @Value("${nlp.word2vec.weight.write.path}")
    private String wordWeightPath;

    @Value("${nlp.word2vec.morpheme_mapping.write.path}")
    private String wordMappingPath;

    @Override
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
        if (!fileStore.isExisted(wordMappingPath)&&!fileStore.isExisted(wordWeightPath)){
            saveMapping();
            saveWeight();
        }
    }

    private void saveWeight() {
        StringBuilder sb = getStringBuilderByArray(destinationWordVector.getInputHiddenWeight()).append(getStringBuilderByArray(destinationWordVector.getHiddenOutputWeight()));
        fileStore.saveTextFile(sb.toString(), wordWeightPath);
    }

    private StringBuilder getStringBuilderByArray(double[][] array){
        StringBuilder sb = new StringBuilder();
        for (double[] wordIdx: array){
            for (double weight: wordIdx){
                sb.append(weight).append("\t");
            }
            sb.append("\n");
        }
        return sb;
    }

    private void saveMapping() {
        Map<String, Integer> wordIdxMap = morpheme.getWordIdxMap();
        StringBuilder mappingText = new StringBuilder();
        wordIdxMap.keySet().forEach(word->mappingText.append(word).append("\t").append(wordIdxMap.get(word)).append("\n"));
        fileStore.saveTextFile(mappingText.toString(), wordMappingPath);
    }
}
