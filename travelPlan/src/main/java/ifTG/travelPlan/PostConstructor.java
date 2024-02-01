package ifTG.travelPlan;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.repository.springdata.travel.sucategory.*;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.TourApi;
import ifTG.travelPlan.service.api.TourApiDetailIntro;
import ifTG.travelPlan.service.destination.DestinationSaveByTourApi;
import ifTG.travelPlan.service.destination.DestinationVectorService;
import ifTG.travelPlan.service.destination.EDestinationSaveService;
import ifTG.travelPlan.service.destination.SubDestinationSaveByTourApi;
import ifTG.travelPlan.service.travelplan.search.*;
import ifTG.travelPlan.service.travelplan.search.machineleaning.DestinationOverViewVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.TextRankWeight;
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

    @PostConstruct
    @Transactional
    public void initData() throws IOException {
//        for (int i =0; i<downloadPage; i++){
//            List<Destination> savedDestination = destinationSaveByTourApi.save(i);
//            subDestinationSaveByTourApi.save(savedDestination);
//        }
        /*
        saveEDestination();*/

        machineLeaning.init();
        morpheme.getWordMap().values().forEach(System.out::println);
        System.out.println("높 > " + textRankWeight.getScore("에스프레소", "카페라떼"));
        System.out.println("낮 > " + textRankWeight.getScore("쇼핑", "오락"));
        System.out.println("높 > " + textRankWeight.getScore("저온", "숙성"));
        System.out.println("낮 > " + textRankWeight.getScore("밖", "백숙"));
        System.out.println("5 > " + textRankWeight.getScore("외부", "밖"));
        System.out.println("6 > " + textRankWeight.getScore("바다", "밖"));
    }

    private void saveEDestination() {
        List<Destination> allDestinationList = destinationRepository.findAll();
        eDestinationSaveService.saveEDestination(allDestinationList);
    }
}