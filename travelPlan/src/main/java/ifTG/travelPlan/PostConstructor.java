package ifTG.travelPlan;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.*;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.TourApi;
import ifTG.travelPlan.service.api.TourApiDetailIntro;
import ifTG.travelPlan.service.destination.DestinationSaveByTourApi;
import ifTG.travelPlan.service.destination.EDestinationSaveService;
import ifTG.travelPlan.service.destination.SubDestinationSaveByTourApi;
import ifTG.travelPlan.service.travelplan.search.*;
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

    //@PostConstruct
    @Transactional
    public void initData() throws IOException {
        for (int i =0; i<downloadPage; i++){
            List<Destination> savedDestination = destinationSaveByTourApi.save(i);
            subDestinationSaveByTourApi.save(savedDestination);
        }
        machineLeaning.init();
        saveEDestination();
    }

    private void saveEDestination() {
        List<Destination> allDestinationList = destinationRepository.findAll();
        eDestinationSaveService.saveEDestination(allDestinationList);
    }
}