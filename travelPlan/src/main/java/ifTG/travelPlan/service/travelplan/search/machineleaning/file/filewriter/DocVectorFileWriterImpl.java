package ifTG.travelPlan.service.travelplan.search.machineleaning.file.filewriter;

import ifTG.travelPlan.service.filestore.FileStore;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.docvector.DestinationVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.vector.VectorCalculating;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DocVectorFileWriterImpl implements DocVectorFileWriter {

    @Value("${nlp.doc2vec.similarity.write.path}")
    private String docSimilarityPath;

    private final FileStore fileStore;

    private final DestinationVector destinationVector;

    @Override
    public void saveDocSimilarityFile(){
        StringBuilder text = new StringBuilder();
        Map<Long, Integer> mappingDestinationIdToIdx = destinationVector.getMapping();
        Map<Integer, Long> mappingIdxToDestinationId = mappingDestinationIdToIdx.keySet().stream().collect(Collectors.toMap(mappingDestinationIdToIdx::get, destinationId -> destinationId));
        long size = mappingIdxToDestinationId.size();
        for (int i =0 ;i<size;i++){
            double[] vectorA = destinationVector.getVectorByIdx(i);
            for (int j=i+1; j<size; j++){
                double[] vectorB = destinationVector.getVectorByIdx(j);
                String cosineSimilarity = String.format("%.1f", (VectorCalculating.cosineSimilarity(vectorA, vectorB)+1)/2*100);
                text
                        .append(mappingIdxToDestinationId.get(i))
                        .append("\t")
                        .append(mappingIdxToDestinationId.get(j))
                        .append("\t")
                        .append(cosineSimilarity)
                        .append("\n");
            }
        }
        fileStore.saveTextFile(text.toString(), docSimilarityPath);
    }
}
