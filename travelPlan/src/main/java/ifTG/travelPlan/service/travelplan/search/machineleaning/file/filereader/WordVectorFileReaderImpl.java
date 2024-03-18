package ifTG.travelPlan.service.travelplan.search.machineleaning.file.filereader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifTG.travelPlan.service.filestore.FileStore;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.WordVector;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WordVectorFileReaderImpl implements WordVectorFileReader{
    private final FileStore fileStore;

    @Value("${nlp.word2vec.file.weight.read.path}")
    private String wordWeightPath;

    @Override
    public WordVector readWordWeight(){
        String file = null;
        if (fileStore.isExisted(wordWeightPath)){
            file = fileStore.findFile(wordWeightPath);
            try{
                return new ObjectMapper().readValue(file, WordVector.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }else return null;

    }
}
