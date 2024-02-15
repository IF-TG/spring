package ifTG.travelPlan.service.travelplan.search;

import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.DestinationWordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.docvector.DestinationVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.WordVectorInitializer;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.WordVectorInitializerImpl;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.filewriter.DocVectorFileWriter;
import ifTG.travelPlan.service.travelplan.search.machineleaning.file.filewriter.WordVectorFileWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class MachineLearningImpl implements MachineLeaning {
    private final Morpheme morpheme;
    private final WordVectorInitializer initializer;
    private final DestinationVector destinationVector;
    private final DocVectorFileWriter docVectorFileWriter;
    private final WordVectorFileWriter wordVectorFileWriter;
    @Override
    public void init() {
        log.info("MachineLearningImpl.init start At {}", LocalDateTime.now());
        log.info("InitDestinationWordVector.init start At {}", LocalDateTime.now());
        initializer.initDestinationWordVector();
        log.info("destinationVector.init start At {}", LocalDateTime.now());
        destinationVector.init();
        log.info("docVectorFileWriter.init start At {}", LocalDateTime.now());
        docVectorFileWriter.saveDocSimilarityFile();
        log.info("wordVectorFileWriter.init start At {}", LocalDateTime.now());
        wordVectorFileWriter.saveFile();
    }
}
