package ifTG.travelPlan.service.travelplan.search.machineleaning.fileWriter;

import ifTG.travelPlan.service.filestore.FileStore;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.DestinationVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.vector.VectorCalculating;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DocVectorFileWriterImpl implements DocVectorFileWriter {

    @Value("${nlp.doc2vec.similarity.write.path}")
    private String docSimilarityPath;

    private final FileStore fileStore;

    private final DestinationVector destinationVector;

    public void saveDocSimilarityFile(){
        StringBuilder text = new StringBuilder();
        Map<Long, Integer> mappingDestinationIdToIdx = destinationVector.getMapping();
        Map<Integer, Long> mappingIdxToDestinationId = mappingDestinationIdToIdx.keySet().stream().collect(Collectors.toMap(mappingDestinationIdToIdx::get, destinationId -> destinationId));
        long size = mappingIdxToDestinationId.size();
        for (long i =0 ;i<size;i++){
            double[] vectorA = destinationVector.getVectorByDestinationId(i);
            for (long j=i+1; j<size; j++){
                double[] vectorB = destinationVector.getVectorByDestinationId(j);
                double similarity = VectorCalculating.cosineSimilarity(vectorA, vectorB);
                text
                        .append(mappingIdxToDestinationId.get((int)i))
                        .append("\t")
                        .append(mappingIdxToDestinationId.get((int)j))
                        .append("\t")
                        .append(similarity)
                        .append("\n");
            }
        }
        fileStore.saveTextFile(text.toString(), docSimilarityPath);
    }
}
