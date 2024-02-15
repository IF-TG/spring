package ifTG.travelPlan;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.service.destination.DestinationSaveByTourApi;
import ifTG.travelPlan.service.destination.EDestinationSaveService;
import ifTG.travelPlan.service.destination.SubDestinationSaveByTourApi;
import ifTG.travelPlan.service.travelplan.search.MachineLeaning;

import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.wordvector.DestinationWordVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.TextRankWeight;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostConstructor {
    private final DestinationRepository destinationRepository;
    private final DestinationSaveByTourApi destinationSaveByTourApi;
    private final SubDestinationSaveByTourApi subDestinationSaveByTourApi;
    private final MachineLeaning machineLeaning;
    private final EDestinationSaveService eDestinationSaveService;
    @Value("${api.tour.download_page}")
    private int downloadPage;

    private final TextRankWeight textRankWeight;
    private final Morpheme morpheme;
    private final DestinationWordVector destination;

    @PostConstruct
    @Transactional
    public void initData() throws IOException {
//        for (int i =0; i<downloadPage; i++){
//            List<Destination> savedDestination = destinationSaveByTourApi.save(i);
//            subDestinationSaveByTourApi.save(savedDestination);
//        }
        /*
        */
        machineLeaning.init();
        //saveEDestination();
        //destination.getVectorMapByString("대표");
    }

    private void saveEDestination() {
        List<Destination> allDestinationList = destinationRepository.findAll();
        eDestinationSaveService.saveEDestination(allDestinationList);
    }
}