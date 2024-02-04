package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.DestinationOverViewVectorV2;
import ifTG.travelPlan.service.travelplan.search.machineleaning.TextRank;
import ifTG.travelPlan.service.travelplan.search.machineleaning.VectorAverage;
<<<<<<< HEAD
import ifTG.travelPlan.service.travelplan.search.machineleaning.DestinationOverViewVector;
=======
>>>>>>> d44af60ff76f20667a6a68c636650c12d1b637a9
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EDestinationSaveServiceImpl implements EDestinationSaveService{
    private final EDestinationRepository eDestinationRepository;
    private final TextRank textRank;
    private final VectorAverage vectorAverage;
<<<<<<< HEAD
    private final DestinationOverViewVector destinationOverViewVector;
=======
    private final DestinationOverViewVectorV2 word2Vec;
>>>>>>> d44af60ff76f20667a6a68c636650c12d1b637a9

    @Override
    public void saveEDestination(List<Destination> destinationList) {
        destinationList.forEach(d->{
            List<String> keywordList = textRank.textRank(d.getOverview());
            double[] vectorAverageArray = null;
            if (keywordList.size()==10){
                vectorAverageArray = vectorAverage.getVectorAverage(keywordList.stream().map(destinationOverViewVector::getVectorByString).toList());
            }

            EDestination eDestination = getEDestination(d, keywordList, vectorAverageArray);
            eDestinationRepository.save(eDestination);
        });
    }

    private static EDestination getEDestination(Destination d, List<String> keywordList, double[] vectorAverageArray) {
        return EDestination.builder()
                .id(d.getId())
                .title(d.getTitle())
                .keywordList(keywordList)
                .thumbnailUrl(d.getThumbNail())
                .info(d.getOverview())
                .address(d.getAddress())
                .category(d.getCategory())
                .contentType(d.getContentType())
                .embedding(vectorAverageArray)
                .build();
    }
}
